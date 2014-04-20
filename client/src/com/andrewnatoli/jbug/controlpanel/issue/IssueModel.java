package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
