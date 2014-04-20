package com.andrewnatoli.jbug.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.andrewnatoli.jbug.resources.*;

/**
 * Our main window where users will be able to register and login
 */
public class MainMenuView extends JFrame{

    private ImagePanel  panel;
    private JPanel      loginForm;
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

                btn_login.setVisible(false);
                btn_register.setVisible(false);
                loginForm.setVisible(true);
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
                loginForm.setVisible(false);
                btn_auth.setVisible(false);
                btn_cancel.setVisible(false);
                btn_login.setVisible(true);
                btn_register.setVisible(true);
            }
        });

        btn_register    = new JButton("Register");

        btn_login.setBounds(480,155,100,40);
        btn_register.setBounds(590,155,100,40);

        panel.add(btn_login);
        panel.add(btn_register);

        /*
            Components for the login form
            NOT visible by default
         */

        //Define login and register fields
        loginForm = new JPanel();
        loginForm.setLayout(new GridLayout(3, 2));

        //Username input
        JLabel lbl_user             = new JLabel("Username");
        JTextField input_user       = new JTextField(30);

        //Password input
        JLabel lbl_pass             = new JLabel("Password");
        JTextField input_pass       = new JPasswordField(30);


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
                JOptionPane.showMessageDialog(null, "Logging in!");
            }
        });

        //Assemble the window
        loginForm.setBounds(450, 50, 300, 75);
        loginForm.setOpaque(false);
        loginForm.setVisible(false);
        btn_cancel.setVisible(false);
        btn_auth.setVisible(false);
        panel.add(loginForm);
        panel.add(btn_auth);
        panel.add(btn_cancel);

        /*
            Assemble the GUI and show it to our visitor
         */

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
