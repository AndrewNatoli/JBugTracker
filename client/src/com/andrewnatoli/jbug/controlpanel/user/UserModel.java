package com.andrewnatoli.jbug.controlpanel.user;

import com.andrewnatoli.jbug.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class UserModel {
    private int     user_id;
    private String  name;
    private String  email;
    private String  password;
    private Date    date_created;

    public UserModel(int id) {
        try {
            ResultSet rs = Database.stmt.executeQuery("SELECT * FROM jbug_users WHERE user_id=\""+id+"\";");
            while(rs.next()) {
                user_id         = rs.getInt("user_id");
                name            = rs.getString("name");
                email           = rs.getString("email");
                password        = rs.getString  ("password");
                date_created    = rs.getDate("date_created");
            }
        }
        catch(SQLException e) {
            System.err.println(e.getStackTrace());
            System.err.println("Error loading UserModel: " + e.getMessage());
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate_created() {
        return date_created;
    }
}
