
package musicsocial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChattingServer {

    private static final int PORT = 1337; //static (field is at class level) final (value can't change
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        //sets up socket listener
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
//varibale init

        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            //sockets handler
            this.socket = socket;
        }

        public void run() {
            //send out submit name and then it waits for response and 
            //if that name is unique it adds it to the names if its not unique it goes round again
            try {

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            System.out.println("New Chatter:" + name);
                            out.println("NAMEACCEPTED " + name);
                            break;
                        }
                    }
                }
//this adds the client to the list of sockets
                writers.add(out);
//waiting for message to come into server so can send out to everyone
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE" + name + ": " + input);
                        System.out.println("MESSAGE FROM " + name + ": " + input);
                    }
                }
                //handle whether client crashes and removes client from list of sockets
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
