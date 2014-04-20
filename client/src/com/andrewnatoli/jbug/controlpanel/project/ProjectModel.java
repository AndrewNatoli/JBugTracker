package com.andrewnatoli.jbug.controlpanel.project;

import com.andrewnatoli.jbug.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * Created by andrew on 4/20/14.
 */
public class ProjectModel {
    private int     project_id;
    private String  title;
    private Date    date_created;
    private int     user_id;

    public ProjectModel(int id) {
        try {
            ResultSet rs = Database.stmt.executeQuery("SELECT * FROM jbug_projects WHERE project_id=\""+id+"\";");
            while(rs.next()) {
                project_id   = rs.getInt("project_id");
                title        = rs.getString("title");
                date_created = rs.getDate("date_created");
                user_id      = rs.getInt("user_id");
            }
        }
        catch(SQLException e) {
            System.err.println(e.getStackTrace());
            System.err.println("Error loading ProjectModel: " + e.getMessage());
        }
    }

    public int getProject_id() {
        return project_id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate_created() {
        return date_created;
    }

    public int getUser_id() {
        return user_id;
    }
}
