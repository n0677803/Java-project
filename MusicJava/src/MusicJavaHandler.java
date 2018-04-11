import java.net.*;
import java.io.*;
import java.util.*;

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
                        PrintWriter pout = new PrintWriter(fout,true); 
                        //write to the file
                        for (int i = from; i < to; i++ ) {
                            pout.print(text[i] + ",");
                        }
                        pout.println("");
                        pout.close(); // close the stream
                    } else if ("HndlLog".equals(text[0])) {
                        String fileName = "userData.txt";
                        // now create the filestream and connect PrintWriter
                        // the value true enables autoflushing
                        try {
                            FileReader fin = new FileReader(fileName);
                            BufferedReader din = new BufferedReader(fin);
                            //read from the file
                            String line = null; // line of text
                            int numPoints = 0; // running total of points
                            int arSize = 0;
                            
                            while (din.readLine() != null) arSize++;
                            
                            String[] userdata = new String[arSize];
                            while ((line = din.readLine()) != null) {
                                // here we have read in a line of text
                                // now parse line to extract data and print it out to the screen
                                StringTokenizer st = new StringTokenizer(line, ",");
                                for(int i = 0; i<=1;i++){
                                    userdata[numPoints] = st.nextToken().trim() + "**" + st.nextToken().trim();
                                }

                                numPoints++;
                                
                            }
                            din.close(); // close the stream
                            //loop checking text[1] against all the values in userdata
                            String userlogged;
                            boolean loginsuccess = false;
                            for(int j = 0;j<arSize;j++){
                                if(userdata[j].equals(text[1])){
                                    userlogged = userdata[j];
                                    loginsuccess = true;
                                    j = arSize+1;
                                    DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
                                    outToClient.writeUTF(userlogged);
                                }
                                
                            }
                            
                            
                            
                        } catch (IOException e) {
                            System.err.println("Error! - " + e.getMessage());
                        }
                    }
                   
                } catch (ClassNotFoundException a) {
                    //handle error
                }
            
            
            
        }catch(IOException e){}
        //an entire task can be implemented here
    }   //thread method
}
