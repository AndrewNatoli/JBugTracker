package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Date;

public class IssueModel {

    private int issue_id;
    private int project_id;
    private int user_id;
    private String title;
    private String description;
    private String date_created;
    private String date_closed;
    private int open;
    private int priority;

    /**
     * Fetches our issue from the database
     * @param id
     */
    public IssueModel(int id) {
        try {
            System.out.println("Loading an IssueModel! ID: " + id);
            ResultSet rs = Database.stmt.executeQuery("SELECT * FROM jbug_issues WHERE issue_id=\""+id+"\";");
            while(rs.next()) {
                issue_id    = rs.getInt("issue_id");
                project_id  = rs.getInt("project_id");
                user_id     = rs.getInt("user_id");
                title       = rs.getString("title");
                description = rs.getString("description");
                date_created= rs.getString("date_created");
                date_closed = rs.getString("date_closed");
                open        = rs.getInt("open");
                priority    = rs.getInt("priority");

            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading IssueModel: " + e.getMessage());
        }
        System.out.println("Got the IssueModel. Enjoy it.");
    }

    public int getIssue_id() {
        return issue_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getDate_closed() {
        return date_closed;
    }

    public int getOpen() {
        return open;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * update()
     * Saves the model into the database
     */
    public void update() {
        String q;
        //Create a new issue record in the database
        if(project_id == -1) {
            q = "INSERT INTO `jbug_issues` ( `title`, `description`, `user_id`, `project_id`, `date_created`, `open`, `priority`) values ( '"+title+"', '"+description+"', '"+user_id+"', '"+project_id+"', '"+date_created+"', '"+open+"', '"+priority+"')";
            try {
                Statement insertStatement = Database.conn.createStatement();
                insertStatement.executeUpdate(q);
                insertStatement.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
                System.err.println("[IssueModel] Could not insert new issue into database");
                System.err.println(e.getMessage());
            }
        }
        //Update an existing record
        else {
            q = "UPDATE `jbug_issues` SET `title`='"+title+"', `description`='"+description+"' WHERE `issue_id`='"+issue_id+"'";
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

    /**
     * delete() - Deletes the issue from the database
     * @return boolean Whether or not the query succeeded.
     */
    public boolean delete() {
        String q;
        //Delete our project from the database
        q= "DELETE FROM `jbug_issues` WHERE `issue_id`='"+issue_id+"'";
        try {
            Statement deleteStatement = Database.conn.createStatement();
            deleteStatement.executeUpdate(q);
            deleteStatement.close();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("[IssueModel] Could not delete issue " + issue_id);
            System.out.println(e.getMessage());
            return false;
        }
    }
}
