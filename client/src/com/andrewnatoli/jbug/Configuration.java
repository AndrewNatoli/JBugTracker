package com.andrewnatoli.jbug;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.io.*;

/**
 * Created by andrew on 4/19/14.
 */
public class Configuration {
    private static String dbHost;
    private static String dbUser;
    private static String dbPass;
    private static String dbDatabase;


    /**
     * createNewConfiguration - Create a new db configuration file
     * @param isCorrupted int 1 tells us the config file was corrupted. 0 is for a new user
     */
    public static void createNewConfiguration(int isCorrupted) {
        System.out.println("Creating a new configuration!");
        if(isCorrupted == 1)
            JOptionPane.showMessageDialog(null,"It looks like your configuration file was corrupted. Let's fix that.");
        else if(isCorrupted == 0) {
            JOptionPane.showMessageDialog(null,"Welcome to JBugTracker!");
            JOptionPane.showMessageDialog(null,"Before you can start using the program we'll need to configure your database.");
        }
        String newHost = JOptionPane.showInputDialog(null,"Enter address:port");
        String newUser = JOptionPane.showInputDialog(null,"Enter database username");
        String newPass = JOptionPane.showInputDialog(null,"Enter database password");
        String newDatabase= JOptionPane.showInputDialog(null,"Enter name of database");
        if (Database.testConnection(newHost,newUser,newPass,newDatabase)) {
            JOptionPane.showMessageDialog(null,"Connection successful! Click okay and we'll get the database set up for you.");
            writeConfiguration(newHost,newUser,newPass,newDatabase);
            loadConfiguration(); //Reload it
            Database.runInstaller(); //Install it!
        }
        else {
            JOptionPane.showMessageDialog(null,"Could not connect to the database. Please try again.");
            createNewConfiguration(-1); //Skip the welcome message and go straight to user input
        }
    }

    private static void writeConfiguration(String host, String user, String pass, String database) {
        try {
            FileOutputStream outStream = new FileOutputStream("config.dat");
            DataOutputStream writer = new DataOutputStream(outStream);
            writer.writeUTF(host);
            writer.writeUTF(user);
            writer.writeUTF(pass);
            writer.writeUTF(database);
            writer.close();
            outStream.close();
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing configuration file.");
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * loadConfiguration()
     * Attempts to load the database configuration
     * If there's an error, it calls createNewConfiguration
     */
    public static void loadConfiguration() {
        System.out.println("Loading configuration...");
        try {
            boolean loadable = true;
            //Create the file if it doesn't exist.
            if(!new File("config.dat").exists()) {
                FileOutputStream newFile = new FileOutputStream("config.dat");
                newFile.close();
                createNewConfiguration(0);
                System.out.println("No configuration found. (no file)");
                return;
            }
            else if(isEmptyFile("config.dat")) {
                System.out.println("No configuration file found (empty file)");
                createNewConfiguration(1);
                return;
            }

            //Load the file
            FileInputStream inStream = new FileInputStream("config.dat");
            DataInputStream loader = new DataInputStream(inStream);
            try {
                System.out.println("Reading db values...");
                dbHost = loader.readUTF();
                dbUser = loader.readUTF();
                dbPass = loader.readUTF();
                dbDatabase = loader.readUTF();
            }
            catch(EOFException e) {
                createNewConfiguration(1);
                System.out.println("Issue while reading values.");
                return;
            }
            loader.close(); //Close the file
            inStream.close();
        }
        catch(IOException e) {
            createNewConfiguration(1);
            System.err.println(e.getStackTrace());
            return;
        }
    }

    /**
     * isEmptyFile(String file)
     *
     * Determines if the specified file, file, is empty.
     * @param file
     * @return boolean
     */
    private static boolean isEmptyFile(String file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                return true;
            }
            else
                return false;
        }
        catch(IOException e) {
            System.out.println("Error checking contents of student database. " + e);
            e.printStackTrace();
        }
        return true;
    }

}
