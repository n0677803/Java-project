package musicsocial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient {
// initializing varibles used later

    BufferedReader in;
    PrintWriter out;
    static JFrame frame = new JFrame("Chat Room");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    public static Boolean startit = false;
    public static String username = "";

    public ChatClient() {
//preparing chat box for communication
        //textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    private void run() throws IOException {
//when startit is false - not logged in
        while (true) {
            if (startit == true) {
                // String serverAddress = getServerAddress();
                //opens up socket connection to the server on port 1337
                //starts on the in connection a buffer relay and gets the input of anyone connecting to 1337
                //then preps writer
                Socket socket = new Socket("localhost", 1337);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
//while true = interpreted messages from server
//if the client receives submit name then it has to respond with username
//if its name accpet then its ready for chat
//if it recieves messgae then it will remove first 7 characters and add message to chat area
                while (true) {
                    String line = in.readLine();
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(username);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        frame.setTitle("Main Room - " + line.substring(13));
                        textField.setEditable(true);
                        System.out.println("hey");
                    } else if (line.startsWith("MESSAGE")) {
                        messageArea.append(line.substring(7) + "\n");
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        //preparing jframe
        ChatClient client = new ChatClient();
        //adds evet listener for when jframe window closes
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(false);

        Login login = new Login();
        JFrame loginframe = login;
        loginframe.setVisible(true);

        loginframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//inside sets the title of jfram to current user and makes it ready to start communication by setting starit to true
                System.out.println(login.currentuser);
                frame.setTitle("Main Room - " + login.currentuser);
                client.frame.setVisible(true);
                username = login.currentuser;
                startit = true;
                System.out.println(startit);

            }
        });

        client.run();

    }
}
