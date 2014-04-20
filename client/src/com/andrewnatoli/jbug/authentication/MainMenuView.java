package com.andrewnatoli.jbug.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import com.andrewnatoli.jbug.resources.*;

/**
 * Our main window where users will be able to register and login
 */
public class MainMenuView extends JFrame{

    public MainMenuView() {
        setTitle("JBugTracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(851,250);
        setResizable(false);

        //Create our main panel with a fancy background
        ImagePanel panel = new ImagePanel(new ImageIcon("jbuglogo.png").getImage());
        panel.setLayout(null);

        JButton btn_login       = new JButton("Login");
        JButton btn_register    = new JButton("Register");

        btn_login.setBounds(480,155,100,40);
        btn_register.setBounds(590,155,100,40);

        panel.add(btn_login);
        panel.add(btn_register);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
