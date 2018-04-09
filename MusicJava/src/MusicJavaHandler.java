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
            } catch (ClassNotFoundException a) {
                //handle error
            }
            //code here to write to file
            
        }catch(IOException e){}
        //an entire task can be implemented here
    }   //thread method
}
