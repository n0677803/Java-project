import java.net.*;
import java.io.*;
public class MusicJavaServer {
    public static void main(String[] args) throws IOException {        
        ServerSocket server = new ServerSocket(9090);
        while (true) {
            System.out.println("Waiting for connection...");
            Socket client = server.accept();
            System.out.println("Client " + client.getInetAddress() + " connected!");
            //assign each client to a thread
            MusicJavaHandler t = new MusicJavaHandler(client);
            Thread th = new Thread(t);
            th.start();
         }
    }
}