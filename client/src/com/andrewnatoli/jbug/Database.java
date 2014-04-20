package com.andrewnatoli.jbug;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Database {

    public static Connection conn;
    public static Statement stmt;
    public static String sql;

    private static String user;
    private static String pass;
    private static String db;
    private static String url;

    public static void setAuthentication(String newUser, String newPass, String newDb, String newUrl) {
        url     = newUrl;
        user    = newUser;
        pass    = newPass;
        db      = newDb;
    }

    /**
     * Establishes a connection to the database
     * @return
     */
    public static boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            url = "jdbc:mysql://" + url + "/" + db;
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();
        }
        catch(Exception e) {
            System.err.println(e.getStackTrace());
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Creates a connection to the database to test new credentials. Drops the connection after.
     * @param URL
     * @param USERNAME
     * @param PASSWORD
     * @param DATABASE
     * @return
     */
    public static boolean testConnection(String URL, String USERNAME, String PASSWORD, String DATABASE) {
        setAuthentication(USERNAME,PASSWORD,DATABASE,URL);

        if(connect()) {
            /*
            try {
                conn.close();
            }
            catch(SQLException e) {
                System.err.println("Connection was successful but our disconnect wasn't pretty. " + e.getMessage());
            }
            */
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Installs the database tables necessary to run JBugTracker
     */
    public static void runInstaller() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("install.sql"));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + " ");
            }
            in.close();
            stmt.executeUpdate(sb.toString());
        } catch (Exception e) {
            System.err.println("Failed to install database. " + e.getStackTrace());
            e.printStackTrace();
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"We had a problem installing the database for you.");
            JOptionPane.showMessageDialog(null,"Please execute the contents of install.sql on your MySQL database before continuing to use JBugTracker.");
        }
        JOptionPane.showMessageDialog(null,"Okay, you're ready to go! Enjoy using JBugTracker :)");
    }
}
