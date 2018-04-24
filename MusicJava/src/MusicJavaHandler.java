import java.net.*;
import java.io.*;
import java.nio.file.Files;
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
                            } 
                            new File("dataStorage\\Music\\" + text[1] + "_Music").mkdirs();
                            JOptionPane.showMessageDialog(null, "Registration Success! Welcome: " + text[1]);
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
                    } else if("HndlRetrieve".equals(text[0])){
                        String file_location = dataDir + fileName;
                        String user_input_Name = text[1]; 
                        OoutToClient.writeObject(retrieve_file_record_byname(file_location , get_line_length(file_location) ,user_input_Name));
                    }
                } catch (ClassNotFoundException a) {
                    System.err.println("Error! - " + a.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 89");
                }
        } catch(IOException ai){
            System.err.println("Error! - " + ai.getMessage()); JOptionPane.showMessageDialog(null, "error caught handler around line 92");
        }
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
      StringTokenizer myTokens = null;
      line = reader.readLine();
      myTokens = new StringTokenizer(line, ",");
      line_length = myTokens.countTokens(); //GET THE NUMBER OF TOKENS
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
    }
