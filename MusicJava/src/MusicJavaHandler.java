import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MusicJavaHandler implements Runnable {
    Socket client;
    DataOutputStream outToClient;
    
    public MusicJavaHandler (Socket _client) throws IOException{
        client = _client;
        outToClient = new DataOutputStream(client.getOutputStream());   
        } //constructor
    
    public void run() {
        try{
            ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
            
                try{
                    String[] text = (String[]) inFromClient.readObject();
                    if("HndlReg".equals(text[0])){
                        //code here to write to file
                        String fileName = "userData.txt";
                        int from = 1;
                        int to= text.length;
                
                        FileWriter fout = new FileWriter(fileName,true);
                        //write to the file
                        try (PrintWriter pout = new PrintWriter(fout,true)) {
                            //write to the file
                            for (int i = from; i < to; i++ ) {
                                pout.print(text[i] + ",");
                            }   pout.println("");
                            // close the stream
                        }
                    } else if ("HndlLog".equals(text[0])) {
                        String fileName = "userData.txt";
                        // now create the filestream and connect PrintWriter
                        // the value true enables autoflushing
                        try {
                            FileReader fin = new FileReader(fileName);
                            int arSize;
                            String[] userdata;
                            //read from the file
                            try (BufferedReader din = new BufferedReader(fin)) {
                                //read from the file
                                String line; // line of text
                                line = null;
                                int numPoints = 0; // running total of points
                                arSize = 6;
                                //while (din.readLine() != null) arSize++;
                                //cunt
                                //din = new BufferedReader(fin);
                                userdata = new String[arSize];
                                while ((line = din.readLine()) != null) {
                                    // here we have read in a line of text
                                    // now parse line to extract data and print it out to the screen
                                    StringTokenizer st = new StringTokenizer(line, ",");
                                    //for(int i = 0; i<=1;i++){
                                        userdata[numPoints] = st.nextToken().trim() + "**" + st.nextToken().trim();
                                    //}
                                    
                                    numPoints++;
                                }
                                // close the stream
                                //loop checking text[1] against all the values in userdata
                            } // line of text
                            
                           
                            String userlogged = "";
                            boolean loginsuccess = false;
                            System.out.println("LOOKS AT ME:- " + userdata[0] + text[1]);
                            //JOptionPane.showMessageDialog(null, userdata[0]);
                            //JOptionPane.showMessageDialog(null, text[1]);
                            for(int j = 0;j<arSize;j++){
                                if(userdata[j].equals("Alic**india")){
                                    userlogged = userdata[j];
                                    loginsuccess = true;
                                    j = arSize+1;
                                    
                                    
                                }
                                
                            }
                            outToClient.writeUTF(userlogged);
                            
                            
                        } catch (IOException e) {
                            System.err.println("Error! - " + e.getMessage());
                        }
                    }
                   
                } catch (ClassNotFoundException a) {
                    System.err.println("Error! - " + a.getMessage());
                }
            
            
            
        }catch(IOException ai){System.err.println("Error! - " + ai.getMessage());}
        
    }   //thread method
}
