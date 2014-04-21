package com.andrewnatoli.jbug.controlpanel.project;

import com.andrewnatoli.jbug.Database;
import com.andrewnatoli.jbug.authentication.CurrentUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ProjectModel {
    private int     project_id;
    private String  title;
    private String  date_created;
    private int     user_id;

    /**
     * Select constructor
     * Loads the project specified by project_id in the database
     * @param id The project_id to look for in jbug_projects
     */
    public ProjectModel(int id) {
        try {
            ResultSet rs = Database.stmt.executeQuery("SELECT * FROM jbug_projects WHERE project_id=\""+id+"\";");
            while(rs.next()) {
                project_id   = rs.getInt("project_id");
                title        = rs.getString("title");
                date_created = rs.getString("date_created");
                user_id      = rs.getInt("user_id");
            }
        }
        catch(SQLException e) {
            System.err.println(e.getStackTrace());
            System.err.println("Error loading ProjectModel: " + e.getMessage());
        }
    }

    /**
     * No-argument constructor
     * Used to create new ProjectModels that will be saved to the database
     */
    public ProjectModel() {
        project_id = -1;
        user_id = CurrentUser.getUser_id();
        date_created = "";
        title = "";
    }

    /**
     * getProject_id
     * @return int project_id
     */
    public int getProject_id() {
        return project_id;
    }

    /**
     * getTitle
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getDate_created()
     * @return String date_created
     */
    public String getDate_created() {
        return date_created;
    }

    /**
     * getUser_id
     * @return int user_id that created the project
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * setTitle
     * @param title Sets the title of the project. Need to call update() to save.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * update()
     * Saves the model into the database
     */
    public void update() {
        String q;
        //Create a new project record in the database
        if(project_id == -1) {
            q = "INSERT INTO `jbug_projects` ( `title`, `user_id`, `date_created`) values ( '"+title+"', '"+user_id+"', '"+date_created+"')";
            try {
                Statement insertStatement = Database.conn.createStatement();
                insertStatement.executeUpdate(q);
                insertStatement.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
                System.err.println("[ProjectModel] Could not insert new project into database");
                System.err.println(e.getMessage());
            }
        }
        //Update an existing record
        else {
            q = "UPDATE `jbug_projects` SET `title`='"+title+"', `date_created`='"+date_created+"' WHERE `project_id`='"+project_id+"'";
            try {
                Statement updateStatement = Database.conn.createStatement();
                updateStatement.executeUpdate(q);
                updateStatement.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
                System.err.println("[ProjectModel] Could not update project " + project_id);
                System.err.println(e.getMessage());
            }
        }
    }
}
