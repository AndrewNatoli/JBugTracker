package com.andrewnatoli.jbug;

import com.andrewnatoli.jbug.authentication.MainMenuView;

import javax.swing.*;

/**
 * Created by andrew on 4/19/14.
 */
public class JBugTracker {

    public static void main(String[] args) {
        System.out.println("Starting JBugTracker!");
        Configuration.loadConfiguration();


        if(Database.connected == false) {
            System.out.println("Not connected to database.");
            if(Database.connect() == false) {
                System.err.println("Coudln't connect to database!");
                JOptionPane.showMessageDialog(null,"There was an issue connecting to the database!");
                int redoConfig = JOptionPane.showConfirmDialog(null, "Would you like to reconfigure your database?", "Change database configuration?", JOptionPane.YES_NO_OPTION);
                if (redoConfig == JOptionPane.YES_OPTION)
                    Configuration.createNewConfiguration(-2);
                else
                    System.exit(0);
            }
            else {
                System.out.println("Connected.");
                System.out.println("Showing menu.");
                new MainMenuView();
            }
        }
    }

}
