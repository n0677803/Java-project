import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
public class MusicJavaServer {
    public static void main(String[] args) throws IOException {        
        ServerSocket server = new ServerSocket(9090);
        while (true) {
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException hey){
                JOptionPane.showMessageDialog(null, "error server time unit");
            }
            
            System.out.println("Waiting for connection...");
            Socket client = server.accept();
            System.out.println("Client " + client.getInetAddress() + " connected!");
            //assign each client to a thread
            MusicJavaHandler MJH = new MusicJavaHandler(client);
            Thread MJT = new Thread(MJH);
            MJT.start();
         }
    }
}