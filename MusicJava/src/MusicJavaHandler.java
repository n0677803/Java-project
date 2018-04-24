import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.swing.JOptionPane;

public class MusicJavaHandler implements Runnable {
    Socket client;
    ObjectOutputStream outToClient;
    
    public MusicJavaHandler (Socket _client) throws IOException{
        client = _client;
        outToClient = new ObjectOutputStream(client.getOutputStream());   
        } //constructor
    
    public void run() {
        try{
            ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
            
                try {
                    String[] text = (String[]) inFromClient.readObject(); //retrieves the array
                    if ("HndlReg".equals(text[0])) { ////REGISTER HANDLE ---------------------------------------------------------------------------
                     String fileName = "userData.txt";
                     String dataDir = "dataStorage\\";
                     int from = 1;
                     int to = text.length;
                     FileWriter fout = new FileWriter(dataDir + fileName, true);
                     if (!Username_duplicate_check(dataDir + fileName, text[1])){ //check if the username already exists or not
                      try (PrintWriter pout = new PrintWriter(fout, true)) {
                       //write to the file
                       for (int i = from; i < to; i++) { //startloop
                        pout.print(text[i] + ","); 
                       } /*endloop*/ pout.println(""); //goes onto next line in preparation for the next line
                      } new File("dataStorage\\Music\\" + text[1] + "_Music").mkdirs();
                      JOptionPane.showMessageDialog(null, "Registration Success! Welcome: " + text[1]);
                     } else {
                      JOptionPane.showMessageDialog(null, "Registration failed, username already taken");
                     }
                     
                    } else if ("HndlLog".equals(text[0])) { //LOGIN HANDLE ---------------------------------------------------------------------------
                        String fileName = "userData.txt";
                        String dataDir = "dataStorage\\";
                        String file_location = dataDir + fileName;                        
                        try { //file stuff                            
                            FileReader fin = new FileReader(file_location); 
                            BufferedReader din = new BufferedReader(fin); //Reader                            
                            int arSize = get_file_line_count(file_location); //Size of the file when dragging out the user names
                            boolean user_found = false; //If the user has been found or not
                            boolean loginsuccess = false; //If the user successfully logs in or not                            
                            String[] userdata = new String[arSize];
                            String line = ""; // line of text
                            String user_input_Name = text[1]; //hard coded test values , code to pass them in
                            String user_input_Password = text[2] ; //hard coded test values , code to pass them in 
                            while ((line = din.readLine()) != null){ //Read lines out until the right one is found, or not, retrieves next line every loop
                                StringTokenizer st = new StringTokenizer(line, ","); //Create tokens out of the retrieved line
                                String tempName = st.nextToken().trim();
                                if (user_input_Name.equals(tempName)){ //If the name being searched for equals the token (stored username)
                                    user_found = true;
                                    String retrieved_password = st.nextToken().trim();//Retrieve the password
                                    if (user_input_Password.equals(retrieved_password )){ //Compare the password                                        
                                        loginsuccess = true; //found the username, and the password for it is correct
                                    }
                                    else if (!user_input_Password.equals(retrieved_password)){ //Does not equal
                                        loginsuccess = false; //Found the username, but the password for it is incorrect
                                    } break;                                
                                }
                            } //endloop                
                            if(user_found == false){ //username not found
                                JOptionPane.showMessageDialog(null,"username not found");
                            } else if (user_found == true && loginsuccess == false){ //username found, but password is wrong
                                JOptionPane.showMessageDialog(null, "username found, however the password is incorrect");
                            } else if (user_found == true && loginsuccess == true) {
                                outToClient.writeObject(retrieve_file_record_byname(dataDir + fileName, 11, user_input_Name));
                            } else {
                                int[] lol = {1,2,3}; //bad code but just sending back anything to prevent crash with wrong login
                                outToClient.writeObject(lol);
                            }
                        } catch (IOException e) {
                            System.err.println("Error! - " + e.getMessage());
                        }
                    } /*else if("HndlRetrieve".equals(text[0])){
                        String fileName = "userData.txt";
                        String dataDir = "dataStorage\\";
                        String file_location = dataDir + fileName;
                        String user_input_Name = text[1]; //hard coded test values , code to pass them in
                        retrieve_file_record_byname(file_location , get_line_length(file_location) ,user_input_Name);
                    }*/
                } catch (ClassNotFoundException a) {
                    System.err.println("Error! - " + a.getMessage());
                }
        } catch(IOException ai){System.err.println("Error! - " + ai.getMessage());}
    }   //thread method
    
    
    public int get_file_line_count(String input_filename) { //Retrieves number of lines in a file
     int line_count = 0;
     try { //start try                  
      BufferedReader reader = new BufferedReader(new FileReader(input_filename)); //create reader
      while (reader.readLine() != null) line_count++; //For every line in the file, plus 1       
      reader.close(); //Don't need because netbeans automatically closes reader after try is done
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage());
     }
     return line_count; //Return
    }
    
    public int get_line_length(String input_filename) {
     int line_length = 0;
     try {
      BufferedReader reader = new BufferedReader(new FileReader(input_filename));
      String line;
      StringTokenizer myTokens = null;
      line = reader.readLine();
      myTokens = new StringTokenizer(line, ",");
      line_length = myTokens.countTokens(); //Get the number of tokens
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage());
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
      StringTokenizer myTokens; //Initialize
      while ((line = reader.readLine()) != null) { //start loop
       myTokens = new StringTokenizer(line, ","); //Create tokens out of the retrieved line
       String tempName = myTokens.nextToken().trim();
       if (input_username.equals(tempName)) { //If the name being searched for equals the token (stored username)
        retrieved_record[1] = tempName;
        for (int j = 2; j < retrieved_record.length; j++) { //Populate the array
         retrieved_record[j] = myTokens.nextToken().trim(); //Get all the tokens out
        }
        break; //break out of the loop since the collect username has been found
       } //endif
      } //endloop            
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage());
     }
     return retrieved_record;
    }
    
    public boolean Username_duplicate_check(String input_filename, String input_username) {
     boolean username_found = false; //The resulting boolean whether the username is found or not
     try {
      BufferedReader reader = new BufferedReader(new FileReader(input_filename));
      String line;
      String retrieved_username;
      StringTokenizer myTokens = null;


      while ((line = reader.readLine()) != null) { //start loop
       myTokens = new StringTokenizer(line, ",");
       retrieved_username = myTokens.nextToken(); //retrive the first token, which is the username, and put it into a string
       if (retrieved_username.equals(input_username)) { //If the username input by the user is the same as the one on the current line
        username_found = true;
        break; //Break out of the loop since te username as already been found
       }
      } //endloop - Now put the retrieved token set into the record
     } catch (IOException e) {
      System.err.println("Error! - " + e.getMessage());
     }
     return username_found;
    }
    }
