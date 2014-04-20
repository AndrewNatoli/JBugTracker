package com.andrewnatoli.jbug.authentication;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andrewnatoli.jbug.resources.*;
import com.andrewnatoli.jbug.Database;
import com.andrewnatoli.jbug.tracker.TrackerView;

/**
 * Our main window where users will be able to register and login
 */
public class MainMenuView extends JFrame{

    private ImagePanel  panel;
    private JPanel      loginForm;
    private JPanel      loginPanel;
    private JButton     btn_login;
    private JButton     btn_register;
    private JButton     btn_cancel;
    private JButton     btn_auth;

    public MainMenuView() {
        setTitle("JBugTracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(851,250);
        setResizable(false);

        //Create our main panel with a fancy background
        panel = new ImagePanel(new ImageIcon("jbuglogo.png").getImage());
        panel.setLayout(null);

        btn_login       = new JButton("Login");
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Showing login form");
                panel.setVisible(false);
                btn_login.setVisible(false);
                btn_register.setVisible(false);
                loginPanel.setVisible(true);
                btn_auth.setVisible(true);
                btn_cancel.setVisible(true);
            }
        });

        btn_cancel = new JButton("Go Back");
        btn_cancel.setBounds(590,155,100,40);
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Going back!");
                loginPanel.setVisible(false);
                panel.setVisible(true);
                btn_auth.setVisible(false);
                btn_cancel.setVisible(false);
                btn_login.setVisible(true);
                btn_register.setVisible(true);
            }
        });

        btn_register    = new JButton("Register");
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Coming in a future release!\nFor now login with admin@andrewnatoli.com / admin");
            }
        });

        btn_login.setBounds(480,155,100,40);
        btn_register.setBounds(590,155,100,40);

        panel.add(btn_login);
        panel.add(btn_register);

        /*
            Components for the login form
            NOT visible by default
         */

        loginPanel = new ImagePanel(new ImageIcon("jbuglogo-notext.png").getImage());
        loginPanel.setLayout(null);
        loginPanel.setVisible(false);

        loginForm = new JPanel();
        loginForm.setLayout(new GridLayout(3, 2));

        //Username input
        JLabel lbl_user             = new JLabel("Username");
        final JTextField input_user       = new JTextField(30);

        //Password input
        JLabel lbl_pass             = new JLabel("Password");
        final JTextField input_pass       = new JPasswordField(30);


        //Add them to the form panel
        loginForm.add(lbl_user);
        loginForm.add(input_user);
        loginForm.add(lbl_pass);
        loginForm.add(input_pass);

        //Button that actually does the authentication
        btn_auth = new JButton("Authenticate");
        btn_auth.setBounds(480,155,100,40);
        btn_auth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn_auth.setEnabled(false);
                input_user.setEnabled(false);
                input_pass.setEnabled(false);
                btn_cancel.setEnabled(false);
                if(doLogin(input_user.getText(),input_pass.getText())) {
                    new TrackerView();
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Bad username and password combination.\nTry again.");
                    btn_auth.setEnabled(true);
                    btn_cancel.setEnabled(true);
                    input_user.setEnabled(true);
                    input_pass.setEnabled(true);
                }
            }
        });

        //Assemble the window
        loginForm.setBounds(450, 50, 300, 75);
        loginForm.setOpaque(false);
        loginPanel.setVisible(false);
        loginPanel.add(btn_auth);
        loginPanel.add(btn_cancel);
        loginPanel.add(loginForm);
        btn_cancel.setVisible(false);
        btn_auth.setVisible(false);
        getContentPane().add(loginPanel);

        /*
            Assemble the GUI and show it to our visitor
         */

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }


    /**
     * doLogin - Tries to authenticate the user
     * @param username
     * @param password
     * @return
     */
    private boolean doLogin(String username, String password) {
        String q = "SELECT * FROM jbug_users WHERE email=\""+username+"\" AND password=\""+password+"\";";
        System.out.println(q);
       try {
           ResultSet rs = Database.stmt.executeQuery(q);
           if(rs.next()) {
               System.out.println("Found user " + rs.getString("email"));
               return true;
           }
           else {
               System.out.println("Nothing found.");
               return false;
           }
       }
       catch(SQLException e) {
           System.err.println("Login query failed. " + e.getMessage());
       }
       return false;
    }
}