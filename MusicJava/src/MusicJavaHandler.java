import java.net.*;
import java.io.*;

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
            try{
                String[] text = (String[]) inFromClient.readObject();
                //code here to write to file
                String fileName = "userData.txt";
                int from = 0;
                int to= text.length;
                
                FileWriter fout = new FileWriter(fileName,true);
                PrintWriter pout = new PrintWriter(fout,true); 
                //write to the file
                for (int i = from; i < to; i++ ) {
                    pout.print(text[i] + ",");
                }
                pout.println("");
                pout.close(); // close the stream
            } catch (ClassNotFoundException a) {
                //handle error
            }
            
            
        }catch(IOException e){}
        //an entire task can be implemented here
    }   //thread method
}
