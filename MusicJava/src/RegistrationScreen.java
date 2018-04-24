import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class RegistrationScreen extends javax.swing.JFrame {
    static List<String> Fave_List = new ArrayList<String>(); //List storing faves
    static int Fave_Limit = 3; //How many faves they can have
    
    public RegistrationScreen() {
        initComponents();        
        txt_FaveArea.setRows(Fave_Limit);        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        Lbl_Username = new javax.swing.JLabel();
        Lbl_PlaceOfBirth = new javax.swing.JLabel();
        Lbl_DateOfBirth = new javax.swing.JLabel();
        Lbl_Password = new javax.swing.JLabel();
        txt_Username = new javax.swing.JTextField();
        txt_PlaceOfBirth = new javax.swing.JTextField();
        txt_DateOfBirth = new javax.swing.JTextField();
        cmb_FaveGenres = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        btn_Add = new javax.swing.JButton();
        btn_Remove = new javax.swing.JButton();
        btn_Register = new javax.swing.JButton();
        btn_Login = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_FaveArea = new javax.swing.JTextArea();
        cmb_Month_Select = new javax.swing.JComboBox<>();
        txt_Year = new javax.swing.JTextField();
        txt_Password_input = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_select_file = new javax.swing.JButton();
        txt_file_path = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 400));

        Lbl_Username.setText("Username:");

        Lbl_PlaceOfBirth.setText("Place of Birth:");

        Lbl_DateOfBirth.setText("Date of Birth:");

        Lbl_Password.setText("Password:");

        cmb_FaveGenres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rock and Roll", "Techno", "Classical", "Drum and Bass", "Rap", "Opera" }));

        btn_Add.setText("ADD");
        btn_Add.setMaximumSize(new java.awt.Dimension(80, 23));
        btn_Add.setMinimumSize(new java.awt.Dimension(80, 23));
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Remove.setText("REMOVE");
        btn_Remove.setMaximumSize(new java.awt.Dimension(80, 23));
        btn_Remove.setMinimumSize(new java.awt.Dimension(80, 23));
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        btn_Register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Register.setText("REGISTER");
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        btn_Login.setText("Login");
        btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });

        txt_FaveArea.setEditable(false);
        txt_FaveArea.setColumns(20);
        txt_FaveArea.setRows(5);
        txt_FaveArea.setAutoscrolls(false);
        jScrollPane1.setViewportView(txt_FaveArea);

        cmb_Month_Select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_Month_Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_Month_SelectActionPerformed(evt);
            }
        });

        jLabel1.setText("Profile Picture:");

        btn_select_file.setText("Select file");
        btn_select_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_select_fileActionPerformed(evt);
            }
        });

        txt_file_path.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(btn_Login))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmb_FaveGenres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Lbl_DateOfBirth)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_DateOfBirth)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmb_Month_Select, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Lbl_PlaceOfBirth)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_PlaceOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Lbl_Username)
                                    .addComponent(Lbl_Password))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_Password_input, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addComponent(txt_Username))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_select_file)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txt_file_path))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lbl_Username)
                            .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btn_select_file))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lbl_Password)
                            .addComponent(txt_Password_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_file_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_PlaceOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lbl_PlaceOfBirth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lbl_DateOfBirth)
                            .addComponent(txt_DateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_Month_Select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_FaveGenres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Register, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btn_Login))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    //Add button, add stuff from the combo box to the list
    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // Add the fave music genre to the list    
        Add_String(cmb_FaveGenres.getSelectedItem().toString());       
    }//GEN-LAST:event_btn_AddActionPerformed
    //Function to add strings to the list
    private void Add_String(String input_string){        
        if (!Fave_List.contains(input_string) && Check_Limit()){ //Make sure the thing being added isn't already in the box
            Fave_List.add(input_string); //Add the data
            update_fave_box(); //Update the box with the new array data
        }
    }
    
    private String Combine_genre(){
        String temp = "";
        String my2ndDelimiter = "/"; //Easier to manage if we use a variable
        
        if (!Fave_List.isEmpty())
        { //If there's actual values populating the list
            for (int i = 0; i < Fave_List.size(); i++) //start loop
            {
                //commented ifs for removing final delimiter 
                //   (this/this/this/) >>>
                //>>>(this/this/this)
                //but havent decided if nessesary
                //if (i < Fave_List.size() -1 ){
                temp = temp + Fave_List.get(i) + my2ndDelimiter; //Merge the string
                //} else {
                //        temp = temp + Fave_List.get(i) + "\n"; //Merge the string
               // }
                    
            } //end loop
        }
        else{
            temp = "NO-GENRE-SELECTED";
        }
        
        
        return temp;
    }
  //Remove button, remove stuff from the list
    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed

        Remove_String(cmb_FaveGenres.getSelectedItem().toString());
    }//GEN-LAST:event_btn_RemoveActionPerformed

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        JOptionPane.showMessageDialog(null, "Attempting To Register!");
        
        if(txt_Username.getText().isEmpty() ||txt_Password_input.getText().isEmpty() || txt_PlaceOfBirth.getText().isEmpty() || txt_DateOfBirth.getText().isEmpty() || txt_Year.getText().isEmpty() ){
         // error
         JOptionPane.showMessageDialog(null, "Registration failed, please fill out all the boxes!");
        } else { //every thing is fine, you can continue.

            
            
            
            
        String[] userdata = new String[11];
        String myDelimiter = " "; //Easier to manage if we use a variable
        String my2ndDelimiter = "/"; //Easier to manage if we use a variable
        
        
        
        userdata[0] = "HndlReg";
        userdata[1] = txt_Username.getText(); //
        userdata[2] = txt_Password_input.getText(); //Place of birth
        userdata[3] = txt_PlaceOfBirth.getText(); //Place of birth
        userdata[4] = txt_DateOfBirth.getText() + my2ndDelimiter + cmb_Month_Select.getSelectedItem().toString() + my2ndDelimiter + txt_Year.getText(); //get the date stuff
        userdata[5] = Combine_genre(); //Function to combine all of the genres into one line
        userdata[6] = "noFriends";
        userdata[7] = "noSentRequests";
        userdata[8] = "noRecievedRequests";
        userdata[9] = txt_file_path.getText();       
        userdata[10] = userdata[1] + "_Music";
        new File("dataStorage").mkdir();
        
      
        try{
        Socket server = new Socket("localhost", 9090);
        
        ObjectOutputStream outToServer = new ObjectOutputStream(server.getOutputStream());
        outToServer.writeObject(userdata);
        //here we need to make the server send back a message to confirm registration has worked
        //this is good coding practice
        //just because we can communicate with server, doesnt mean it actually worked

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Registration failed, Connection failed!!!");
            //handle error here
        }
        txt_Username.setText(null);
        txt_PlaceOfBirth.setText(null);
        txt_DateOfBirth.setText(null);
        }
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void cmb_Month_SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_Month_SelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_Month_SelectActionPerformed

    private void btn_select_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_select_fileActionPerformed
        // TODO add your handling code here:
        String path = "C:\\Users\\Public";
        
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, GIF, & PNG Images", "jpg", "gif", "png");
        
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            txt_file_path.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_btn_select_fileActionPerformed

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginActionPerformed
        // TODO add your handling code here:
        this.dispose();//Close this form
        new LoginScreen().setVisible(true);
    }//GEN-LAST:event_btn_LoginActionPerformed
  //--------------------------------------------------------------------------
   //Function to remove strings from the list
    private void Remove_String(String input_string){
        if (Fave_List.contains(input_string)){
            Fave_List.remove(input_string);
            update_fave_box(); //Update the box with the new array data
        }
    }
    
    private void update_fave_box(){
        String myString = "";       
        for (int i = 0; i < Fave_List.size(); i++)
        {
            myString += Fave_List.get(i);
            myString += "\n";
        }     
        txt_FaveArea.setText(myString);    
    }
    
    private boolean Check_Limit(){            
        if (Fave_List.size() < Fave_Limit )
            return true;
        else
            return false;
    }
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
            java.util.logging.Logger.getLogger(RegistrationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrationScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbl_DateOfBirth;
    private javax.swing.JLabel Lbl_Password;
    private javax.swing.JLabel Lbl_PlaceOfBirth;
    private javax.swing.JLabel Lbl_Username;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Login;
    private javax.swing.JButton btn_Register;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JButton btn_select_file;
    private javax.swing.JComboBox<String> cmb_FaveGenres;
    private javax.swing.JComboBox<String> cmb_Month_Select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_DateOfBirth;
    private javax.swing.JTextArea txt_FaveArea;
    private javax.swing.JTextField txt_Password_input;
    private javax.swing.JTextField txt_PlaceOfBirth;
    private javax.swing.JTextField txt_Username;
    private javax.swing.JTextField txt_Year;
    private javax.swing.JTextField txt_file_path;
    // End of variables declaration//GEN-END:variables
}
