package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.Database;
import com.andrewnatoli.jbug.authentication.CurrentUser;

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
                project_id = rs.getInt("project_id");
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

    /**
     * Blank constructor to create a new issue
     */
    public IssueModel() {
        System.out.println("Creating a blank IssueModel");
        issue_id    = -1;
        user_id     = CurrentUser.getUser_id();
        title       = "";
        description = "";
        date_created= "00-00-0000";
        date_closed = "00-00-0000";
        open        = 1;
        priority    = 0;
        System.out.print("Anddddd there's your new Issue.");
    }

    /**
     * getIssue_id()
     * @return int issue_id
     */
    public int getIssue_id() {
        return issue_id;
    }

    /**
     * getProject_id()
     * @return int project_id the issue belongs to
     */
    public int getProject_id() {
        return project_id;
    }

    /**
     * getUser_id()
     * @return int The author ID
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * getTitle()
     * @return String the title of the issue
     */
    public String getTitle() {
        return title;
    }

    /**
     * getDescription()
     * @return String the issue description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getDate_created()
     * @return String the date the issue was posted
     */
    public String getDate_created() {
        return date_created;
    }

    /**
     * getDate_closed()
     * @return String the date the issue was marked as closed
     */
    public String getDate_closed() {
        return date_closed;
    }

    /**
     * getOpen()
     * @return int (0: Closed, 1: Open)
     */
    public int getOpen() {
        return open;
    }

    /**
     * getPriority()
     * @return int 0 (lowest) - 9 (highest)
     */
    public int getPriority() {
        return priority;
    }

    /**
     * setTitle
     * @param title Updates the issue's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setDescription()
     * @param description Updates the issue's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * setOpen
     * @param open (0: Closed, 1: Open)
     */
    public void setOpen(int open) {
        this.open = open;
    }

    /**
     * update()
     * Saves the model into the database
     */
    public void update() {
        String q;
        //Create a new issue record in the database
        if(issue_id == -1) {
            q = "INSERT INTO `jbug_issues` ( `title`, `description`, `user_id`, `project_id`, `date_created`, `open`, `priority`) values ( '"+title+"', '"+description+"', '"+user_id+"', '"+ project_id +"', '"+date_created+"', '"+open+"', '"+priority+"')";
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
                System.err.println("[IssueModel] Could not update issue " + project_id);
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
