import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MusicJavaHandler implements Runnable {
    Socket client;
    DataOutputStream outToClient;
    ObjectOutputStream OoutToClient;
    
    public MusicJavaHandler (Socket _client) throws IOException{
        client = _client;
        outToClient = new DataOutputStream(client.getOutputStream());  
        OoutToClient = new ObjectOutputStream(client.getOutputStream()); 
        } //CONSTRUCTOR
    
    public void run() {
        try{
            ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
            String fileName = "userData.txt";
            String postsFileName = "postData.txt";
            String userFileName = "onlineUsers.txt";
            String dataDir = "dataStorage\\";            
                try {
                    String[] text = (String[]) inFromClient.readObject(); //retrieves the array
//-REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE-
//-REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE--REGISTER HANDLE-
                    if ("HndlReg".equals(text[0])) { 
                        int from = 1;
                        int to = text.length;
                        FileWriter fout = new FileWriter(dataDir + fileName, true);
                        if (!Username_duplicate_check(dataDir + fileName, text[1])){ //CHECK IF THE USERNAME ALREADY EXISTS OR NOT
                            try (PrintWriter pout = new PrintWriter(fout, true)) {
                            //WRITE TO THE FILE
                            for (int i = from; i < to; i++) { //STARTLOOP
                                pout.print(text[i] + ","); 
                            } /*ENDLOOP*/ 
                            pout.println(""); //GOES ONTO NEXT LINE IN PREPARATION FOR THE NEXT LINE
                            pout.close();
                                } //END TRY
                            
                            new File("dataStorage\\Music\\" + text[1] + "_Music").mkdirs();
                            JOptionPane.showMessageDialog(null, "Registration Success! Welcome: " + text[1]);
                            
                            fout.close();
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Registration failed, username already taken");
                        }
//-LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE-
//-LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE--LOGIN HANDLE-
                    } else if ("HndlLog".equals(text[0])) { 
                        String file_location = dataDir + fileName;                        
                        try { //FILE STUFF                            
                            FileReader fin = new FileReader(file_location); 
                            BufferedReader din = new BufferedReader(fin); //READER                            
                            int arSize = get_file_line_count(file_location); //SIZE OF THE FILE WHEN DRAGGING OUT THE USER NAMES
                            boolean user_found = false; //IF THE USER HAS BEEN FOUND OR NOT
                            boolean loginsuccess = false; //IF THE USER SUCCESSFULLY LOGS IN OR NOT                            
                            String[] userdata = new String[arSize];
                            String line = ""; // LINE OF TEXT
                            String user_input_Name = text[1];
                            String user_input_Password = text[2] ;
                            while ((line = din.readLine()) != null){ //READ LINES OUT UNTIL THE RIGHT ONE IS FOUND, OR NOT, RETRIEVES NEXT LINE EVERY LOOP
                                StringTokenizer st = new StringTokenizer(line, ","); //CREATE TOKENS OUT OF THE RETRIEVED LINE
                                String tempName = st.nextToken().trim();
                                if (user_input_Name.equals(tempName)){ //IF THE NAME BEING SEARCHED FOR EQUALS THE TOKEN (STORED USERNAME)
                                    user_found = true;
                                    String retrieved_password = st.nextToken().trim();//RETRIEVE THE PASSWORD
                                    if (user_input_Password.equals(retrieved_password )){ //COMPARE THE PASSWORD                                        
                                        loginsuccess = true; //FOUND THE USERNAME, AND THE PASSWORD FOR IT IS CORRECT
                                    }
                                    else if (!user_input_Password.equals(retrieved_password)){ //DOES NOT EQUAL
                                        loginsuccess = false; //FOUND THE USERNAME, BUT THE PASSWORD FOR IT IS INCORRECT
                                    } break;                                
                                }
                            } //ENDLOOP                
                            if(user_found == false){ //USERNAME NOT FOUND
                                JOptionPane.showMessageDialog(null,"username not found");
                            } else if (user_found == true && loginsuccess == false){ //USERNAME FOUND, BUT PASSWORD IS WRONG
                                JOptionPane.showMessageDialog(null, "username found, however the password is incorrect");
                            } else if (user_found == true && loginsuccess == true) {
                                JOptionPane.showMessageDialog(null, "user and pass correct should open main screen");
                                MainUserScreen mus = new MainUserScreen(user_input_Name);
                                mus.setVisible(true);
                                //outToClient.writeUTF(user_input_Name);
                                
                            } else { //BAD CODE BUT JUST SENDING BACK ANYTHING TO PREVENT CRASH WITH WRONG LOGIN
                                //outToClient.writeUTF("LINE78HANDLERBADCODE");
                            }
                        } catch (IOException e) {
                            System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 81 hndllog");
                        }
//-RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE-
//-RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE--RETRIEVE HANDLE-
                    } else if("HndlRetrieve".equals(text[0])){
                        String file_location = dataDir + fileName;
                        String user_input_Name = text[1]; 
                        OoutToClient.writeObject(retrieve_file_record_byname(file_location , get_line_length(file_location) ,user_input_Name));
                    }
//-POST RETRIEVE HANDLE---POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE-
//-POST RETRIEVE HANDLE---POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE--POST RETRIEVE HANDLE-
                    else if ("HndlPostRetrieve".equals(text[0]))
                    {//postsFileName
                        String[] postStorage = return_all_posts(dataDir + postsFileName , text[1]);
                        
                        OoutToClient.writeObject(postStorage);
                    }
                    else if ("HndlPostUpload".equals(text[0]))
                    {
                        add_post(dataDir + postsFileName , text[1]);
                      
                    }
                    else if ("HndlLOGIN".equals(text[0]))
                    {
                       log_in_user(dataDir + userFileName ,text[1]);
                    }
                     else if ("HndlLOGOFF".equals(text[0]))
                    {
                        log_out_user(dataDir + userFileName ,text[1]);       
                    }
//-END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS-
//-END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS--END OF IFS-
                } catch (ClassNotFoundException a) {
                    System.err.println("Error! - " + a.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 89");
                }
        inFromClient.close();
        } catch(IOException ai){
            //error "Software caused connection abort: recv failed"
            System.err.println("Error! - " + ai.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 92");
        }
        Close_streams();
    }   //THREAD METHOD
    
    
    public int get_file_line_count(String input_filename) { //RETRIEVES NUMBER OF LINES IN A FILE
     int line_count = 0;
     try { //start try                  
      BufferedReader reader = new BufferedReader(new FileReader(input_filename)); //CREATE READER
      while (reader.readLine() != null) line_count++; //FOR EVERY LINE IN THE FILE, PLUS 1       
      reader.close(); //DON'T NEED BECAUSE NETBEANS AUTOMATICALLY CLOSES READER AFTER TRY IS DONE
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 104 get file line count");
     }
     return line_count; //RETURN
    }
    
    public int get_line_length(String input_filename) {
     int line_length = 0;
     try {
        BufferedReader reader = new BufferedReader(new FileReader(input_filename));
        String line;
        line = reader.readLine();
        StringTokenizer myTokens = new StringTokenizer(line, ",");
        line_length = myTokens.countTokens(); //GET THE NUMBER OF TOKENS
        reader.close();
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 119 get line length");
     }
     return line_length;
    }
    
    public String[] retrieve_file_record_byname(String input_filename, int input_record_length, String input_username) {
     String[] retrieved_record = new String[input_record_length];
     retrieved_record[0] = "HndlMain";
     try {
      BufferedReader reader = new BufferedReader(new FileReader(input_filename));
      String line;
      int i = 0;
      StringTokenizer myTokens; //INITIALIZE
      while ((line = reader.readLine()) != null) { //START LOOP
       myTokens = new StringTokenizer(line, ","); //CREATE TOKENS OUT OF THE RETRIEVED LINE
       String tempName = myTokens.nextToken().trim();
       if (input_username.equals(tempName)) { //IF THE NAME BEING SEARCHED FOR EQUALS THE TOKEN (STORED USERNAME)
        retrieved_record[1] = tempName;
        for (int j = 2; j < retrieved_record.length; j++) { //POPULATE THE ARRAY
         retrieved_record[j] = myTokens.nextToken().trim(); //GET ALL THE TOKENS OUT
        }
        break; //BREAK OUT OF THE LOOP SINCE THE COLLECT USERNAME HAS BEEN FOUND
       } //ENDIF
      } //ENDLOOP            
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 144 retrieve record byname");
     }
     return retrieved_record;
    }
    
    public boolean Username_duplicate_check(String input_filename, String input_username) {
     boolean username_found = false; //THE RESULTING BOOLEAN WHETHER THE USERNAME IS FOUND OR NOT
     try {
      BufferedReader reader = new BufferedReader(new FileReader(input_filename));
      String line;
      String retrieved_username;
      StringTokenizer myTokens = null;


      while ((line = reader.readLine()) != null) { //START LOOP
       myTokens = new StringTokenizer(line, ",");
       retrieved_username = myTokens.nextToken(); //RETRIVE THE FIRST TOKEN, WHICH IS THE USERNAME, AND PUT IT INTO A STRING
       if (retrieved_username.equals(input_username)) { //IF THE USERNAME INPUT BY THE USER IS THE SAME AS THE ONE ON THE CURRENT LINE
        username_found = true;
        break; //BREAK OUT OF THE LOOP SINCE TE USERNAME AS ALREADY BEEN FOUND
       }
      } //ENDLOOP - NOW PUT THE RETRIEVED TOKEN SET INTO THE RECORD
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 167 username duplicate");
     }
     return username_found;
    }
    
    public String[] return_all_posts(String input_filename , String friend_string) {
        
      int arSize = get_line_length(input_filename); //Get the amount of posts, will cut down later     
      int currentArraySize = 0;
      String[] retrieved_posts = new String[arSize + 1]; //create the array with recovered size, +1 to make room for handler
     retrieved_posts[0] = "HndlMain"; //Idk what to set this for now
     
    //NEED TO SPLIT THE FRIENDS INTO AN ARRAY SO THEY CAN BE CHECKED AGAINST THE POSTS 
    
    //Check if they have friends or not

        StringTokenizer friendTokens = new  StringTokenizer(friend_string, "/");//Split the friends list into tokens       
        String[] friendArray = new String[friendTokens.countTokens()]; //Create the array storing the firend names
        
        for (int i = 0; i < friendArray.length; i++)
        {
            friendArray[i] = friendTokens.nextToken(); //Add all the friend tokens into the array
        }//END FOR LOOP
    
    
     try {
        BufferedReader reader = new BufferedReader(new FileReader(input_filename));
        String line;
        StringTokenizer myTokens; //INITIALIZE
        while ((line = reader.readLine()) != null) { //START LOOP
            
            myTokens = new StringTokenizer(line, ":"); //CREATE TOKENS OUT OF THE RETRIEVED LINE
            
            String tempName = myTokens.nextToken().trim();
            String tempPost = myTokens.nextToken();//No need to trim since the post will probably contain spaces
            for (int j = 0; j < friendArray.length; j++)
            {
                //Check if the post author is in the user's friendlist
                if (friendArray[j].equals(tempName))
                {
                    //So because the post author is a friend of the user, we add it into the post array
                    //Either one works, depends if we ever want to change the post delimiter, but if we keep it as : then we can just get the line
                    //String combinedPost = tempName + " : " + tempPost;
                    String combinedPost = line;
                    retrieved_posts[currentArraySize] = combinedPost;
                    currentArraySize++; //Increment, there is now one more post in the array
                    break;
                }
            }//END FOR LOOP
            
        } //END WHILE LOOP     
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 183 return all posts");
     }//ENDCATCH
     
     //The first array started with the max size, just incase all the posts were by friends, however if that isn't the case then the values need to be shifted to a smaller array
     
     if (currentArraySize == arSize + 1) //If all the posts are in the textfile (unlikely)
     {
         return retrieved_posts;
     }
     else
     {
        String[] smallPostsArray = new String[currentArraySize];

            for (int k = 0; k < currentArraySize; k++)
            {
                smallPostsArray[k] = retrieved_posts[k];//Move all the posts into the smaller array
            }
        return smallPostsArray;
     }
    }//ENDFUNCTION
    
    public void add_post(String input_filename , String post)
    {
        JOptionPane.showMessageDialog(null, post);
        try{//FILE TRY
        FileWriter fout = new FileWriter(input_filename, true);
        try (PrintWriter pout = new PrintWriter(fout, true)) {
            //WRITE TO THE FILE
            pout.print(post); //Print the post 
            pout.println(""); //GOES ONTO NEXT LINE IN PREPARATION FOR THE NEXT LINE
            pout.close();
            } //END TRY
        fout.close();              
        }
        catch (IOException e) { //FILE CATCH
            System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 237 hndllog");
        }
    }
    public void log_in_user(String input_filename , String user)
    {
        if (!Username_duplicate_check(input_filename , user))
        {
            try{
                FileWriter fout = new FileWriter(input_filename, true);
                PrintWriter pout = new PrintWriter(fout, true);
                pout.print(user);
                pout.println(""); //next line
                    
                pout.close();
                fout.close();
                JOptionPane.showMessageDialog(null, user + " LOGGED IN");
            }
            catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 289 get line length");
            }
            
        }
    
    }
    public void log_out_user(String input_filename , String user)
    {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input_filename));
            
            FileWriter fout = new FileWriter(input_filename, false); //False so it replaces the file rather than appending
            PrintWriter pout = new PrintWriter(fout, false);
            
            String lineToRemove = user;
            String line;
            
            int limit = get_file_line_count(input_filename) - 1;
            int i = 0;
            String[] tempStorage = new String[limit];
            
            //Rewrite the file WITHOUT the user logging out
            
            //READ THE CONTENTS
            while ((line = reader.readLine()) != null){
                if (!line.equals(lineToRemove))
                {
                    tempStorage[i] = line; //Print the line if the name being removed is the current line
                    JOptionPane.showMessageDialog(null,tempStorage[i]);
                    i++; //Next array element
                }//END IF

            }//ENDWHILE
            
            for (int j = 0; j < limit; j++) { //STARTLOOP
                pout.println(tempStorage[j]); 
            } /*ENDLOOP*/ 
            //Close the file stuff
                     
            reader.close();
            fout.close();
            pout.close();
            
            //tempFile.renameTo(inputFile); //Rename to replace old file

     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 317 [log out]");
     }
    }
    public void Close_streams()
    {
        try{
            
        
            outToClient.close();
            OoutToClient.close(); 
        }
        catch (IOException e){
            System.err.println("Error! - " + e.getMessage()); JOptionPane.showMessageDialog(null, "Error closing streams");
        }
        
    }
    
    }//ENDCLASS
