
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JList; //CAN WE REMOVE THIS IMPORT JOSH?
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthew
 */
public class MainUserScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainUserScreen
     * @param input_username
     */
    
    static String[] tempUserData = new String[11];
    
    public MainUserScreen(String input_username) { //construction
        //mainscreen doesnt open because it cant run all the code need to write code to talk to server to get user details so can populatescreen
        initComponents();
        
        
        tempUserData = Populate_Array(input_username);
        
        //pass the array of info into this and populate the screen
        //Username, Password, PlaceOfBirth,DateOfBirth,FaveGenres,freinds,sentRequests,receivedRequests
        Populate_Screen(tempUserData);
        
        Populate_Posts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lst_friends_display = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_info = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_shared_songs = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_friend_posts = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txt_post = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_send = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        lst_friendslist = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        lst_friend_requests = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_cUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lst_friends_display.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No Friends" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lst_friends_display.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(lst_friends_display);

        jLabel1.setText("Friends");

        txt_info.setColumns(20);
        txt_info.setRows(5);
        jScrollPane2.setViewportView(txt_info);

        jLabel2.setText("Information");

        jScrollPane3.setViewportView(txt_shared_songs);

        jLabel3.setText("Shared Songs");

        jButton1.setText("Play");

        txt_friend_posts.setEditable(false);
        txt_friend_posts.setColumns(20);
        txt_friend_posts.setRows(5);
        jScrollPane4.setViewportView(txt_friend_posts);

        jLabel4.setText("Friends Posts");

        jLabel5.setText("Post:");

        btn_send.setText("Send");
        btn_send.setToolTipText("");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(lst_friendslist);

        jScrollPane6.setViewportView(lst_friend_requests);

        jLabel6.setText("Connected People");

        jButton3.setText("Request Friendship");

        jButton4.setText("Chat");

        jButton5.setText("Accept");

        jButton6.setText("Refuse");
        jButton6.setToolTipText("");

        jLabel7.setText("Friend Requests");

        lbl_cUser.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_post)
                        .addGap(18, 18, 18)
                        .addComponent(btn_send)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(241, 241, 241)
                                .addComponent(jLabel7))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(161, 161, 161)
                .addComponent(jLabel3)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_cUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lbl_cUser)
                            .addGap(21, 21, 21)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btn_send))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );

        txt_post.getAccessibleContext().setAccessibleName("post_text_box");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        Store_Post(lbl_cUser.getText());
        txt_post.setText(""); //Set the text to 
    }//GEN-LAST:event_btn_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
           //new MainUserScreen().setVisible(true);
            }
        });
        
        
    }
    
    private void Populate_Screen(String[] input_user_Data)
    {
        String delim = "\n";
        //INFO BOX STUFF
        String username = input_user_Data[1];
        String PlaceOfBirth = input_user_Data[3];
        String DateOfBirth = input_user_Data[4];
        //Split the fave genre into seperate elements
        String Fave_genre = "";
        StringTokenizer genreTokens = new StringTokenizer(input_user_Data[5], "/"); //Create tokens out of the retrieved line
        
        for (int i = 0; i < genreTokens.countTokens(); i++ )
        {
            Fave_genre += genreTokens.nextToken().trim(); //Add the string onto it
            Fave_genre += "\n"; //Delimiter next line
        }
        
//                String tempName = myTokens.nextToken().trim();
//                if (input_username.equals(tempName)) //If the name being searched for equals the token (stored username)
//                {
//                    for (int j = 0; j < retrieved_record.length; j++) //Populate the array
//                        {
//                            retrieved_record[j] = myTokens.nextToken().trim(); //Get all the tokens out
//                        }
//                    break; //break out of the loop sin
      
        String tempInfoString = username + delim + PlaceOfBirth + delim + DateOfBirth + delim + Fave_genre;
      
        txt_info.setText(tempInfoString); //Set text
        lbl_cUser.setText("welcome " + username);
        
        //LISTBOX VIDEO https://www.youtube.com/watch?v=oA_kcVaJQ3E
        //FRIENDS BOX STUFF
        String FriendsString = input_user_Data[6];
        if (FriendsString.equals("noFriends"))
        {
            //lst_friends_display
            DefaultListModel tempModel = new DefaultListModel();
            tempModel.addElement("You have no friends");
            lst_friends_display.setModel(tempModel);
        }
        else
        {
            //SET THE DELIMITER HERE TO WHAT IT'S ACTUALLY STORED AS
            String FriendDelimiter = "/"; //Unique looking delimiter so usernames have low chance to contain them
            DefaultListModel tempModel = new DefaultListModel();
            
            StringTokenizer friendTokens = new StringTokenizer(FriendsString, FriendDelimiter); //Create tokens out of the retrieved line
        
            for (int i = 0; i < friendTokens.countTokens(); i++ )
            {
                tempModel.addElement(friendTokens.nextToken().trim()); //Add the string onto it

            }
            //Set all the values onto the actual listbox
            lst_friends_display.setModel(tempModel);
            
        }
        
        //RECIEVED FRIEND REQUEST STUFF
        if (input_user_Data[8].equals("noRecievedRequests"))
        {   //lst_friend_requests
            DefaultListModel tempModel = new DefaultListModel();
            tempModel.addElement("You have no recieved friend requests");
            lst_friend_requests.setModel(tempModel);
        }
        else
        {
            String FriendDelimiter = "-|-"; //Unique looking delimiter so usernames have low chance to contain them
            DefaultListModel tempModel = new DefaultListModel();
            
            StringTokenizer friendTokens = new StringTokenizer(input_user_Data[5], FriendDelimiter); //Create tokens out of the retrieved line
        
            for (int i = 0; i < friendTokens.countTokens(); i++ )
            {
                tempModel.addElement(friendTokens.nextToken().trim()); //Add the string onto it

            }
            //Set all the values onto the actual listbox
            lst_friend_requests.setModel(tempModel);
            
        }
        
    }
    
    private String[] Populate_Array(String username)
    {
        String[] userdata = new String[11];
        userdata[0] = "HndlRetrieve";
        userdata[1] = username;
        
        try(Socket server = new Socket("localhost", 9090);){ //new socket named server with name local host and port 9090
            ObjectOutputStream outToServer = new ObjectOutputStream(server.getOutputStream());
            outToServer.writeObject(userdata); //send the login details to server>>handler which validates and returns data
            ObjectInputStream inFromServer = new ObjectInputStream(server.getInputStream());
            try{
                userdata = (String[]) inFromServer.readObject();                
            } catch (ClassNotFoundException p) {
                JOptionPane.showMessageDialog(null, "error caught mainuser around line 400 server");
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error caught mainuser around line 404 server");
        }
        
            
        return userdata;
    }
    private void Populate_Posts()
    {
            //SET THE DELIMITER HERE TO WHAT IT'S ACTUALLY STORED AS
            String FriendDelimiter = "/"; //Unique looking delimiter so usernames have low chance to contain them
            String username = tempUserData[1];
            String FriendString = username + FriendDelimiter + tempUserData[6];
            
            String[] userdata = new String[11];
            String[] friend_post_list = null;
            userdata[0] = "HndlPostRetrieve";
            userdata[1] = FriendString;

            try(Socket server = new Socket("localhost", 9090);){ //new socket named server with name local host and port 9090
                ObjectOutputStream outToServer = new ObjectOutputStream(server.getOutputStream());
                outToServer.writeObject(userdata); //send the login details to server>>handler which validates and returns data
                ObjectInputStream inFromServer = new ObjectInputStream(server.getInputStream());
                try{
                    friend_post_list = (String[]) inFromServer.readObject();  //retrive friend posts              
                } catch (ClassNotFoundException p) {
                    JOptionPane.showMessageDialog(null, "error caught mainuser around line 400 server");
                    friend_post_list = new String[1];
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "error caught mainuser around line 419 server");
            }
            //IS NULL FOR SOME REASON
            JOptionPane.showMessageDialog(null, friend_post_list);
            if (friend_post_list != null) //If there are posts stored relevent to the user
            {
                String combined_string = "";
                for (int i = 0; i < friend_post_list.length; i++ )
                { 
                    combined_string += friend_post_list[i];
                    combined_string += "\n";
                   // tempModel.addElement(friend_post_list[i]); //Add the posts into the list box
                   JOptionPane.showMessageDialog(null, combined_string);

                }
                //Set all the values onto the actual listbox
                txt_friend_posts.setText(combined_string);
            }
            else //If there are no posts stored relevent to the user
            {
                txt_friend_posts.setText("You have no posts visible, either add some friends or post yourself!");
            }
    }
    private void Store_Post(String input_username)
    {
        String myPost = input_username + " : " + txt_post.getText();
        
        //Mypost code to send to server.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_send;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_cUser;
    private javax.swing.JList<String> lst_friend_requests;
    private javax.swing.JList<String> lst_friends_display;
    private javax.swing.JList<String> lst_friendslist;
    private javax.swing.JTextArea txt_friend_posts;
    private javax.swing.JTextArea txt_info;
    private javax.swing.JTextField txt_post;
    private javax.swing.JList<String> txt_shared_songs;
    // End of variables declaration//GEN-END:variables
}
