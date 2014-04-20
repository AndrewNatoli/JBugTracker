package com.andrewnatoli.jbug;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Database {

    public static Connection conn;
    public static Statement stmt;
    public static String sql;

    public static boolean testConnection(String URL, String USERNAME, String PASSWORD, String DATABASE) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            URL = "jdbc:mysql://" + URL + "/" + DATABASE;
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        try {
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("Connection was successful but our disconnect wasn't pretty. " + e.getMessage());
        }
        return true;
    }

    public static boolean runInstaller() {
        boolean isScriptExecuted = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader("install.sql"));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n ");
            }
            in.close();
            stmt.executeUpdate(sb.toString());
            isScriptExecuted = true;
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            System.err.println("Failed to install database. " + e.getStackTrace());
        }
        return isScriptExecuted;
    }
}
