package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class IssueModel {

    private int issue_id;
    private int project_id;
    private int user_id;
    private String title;
    private String description;
    private Date date_created;
    private Date date_closed;
    private int open;
    private int priority;

    private String project_title;
    private String user_name;

    /**
     * Fetches our issue from the database
     * @param id
     */
    public IssueModel(int id) {
        try {

            String q = "SELECT \n" +
                    "\tjbug_issues.*, \n" +
                    "\tjbug_projects.title, \n" +
                    "\tjbug_users.`name` \n" +
                    "FROM\n" +
                    "\tjbug_issues,\n" +
                    "\tjbug_projects,\n" +
                    "\tjbug_users\n" +
                    "WHERE\n" +
                    "\tjbug_issues.issue_id = 1 AND\n" +
                    "\tjbug_projects.project_id = jbug_issues.issue_id AND\n" +
                    "\tjbug_users.user_id\t\t = jbug_issues.user_id";

            ResultSet rs = Database.stmt.executeQuery(q);
            while(rs.next()) {
                issue_id        = rs.getInt("jbug_issues.issue_id");
                project_id      = rs.getInt("jbug_issues.project_id");
                user_id         = rs.getInt("jbug_issues.user_id");
                title           = rs.getString("jbug_issues.title");
                description     = rs.getString("jbug_issues.description");
                date_created    = rs.getDate("jbug_issues.date_created");
                date_closed     = rs.getDate("jbug_issues.date_closed");
                open            = rs.getInt("jbug_issues.open");
                priority        = rs.getInt("jbug_issues.priority");
                project_title   = rs.getString("jbug_projects.title");
                user_name       = rs.getString("jbug_users.name");
            }
        }
        catch(SQLException e) {
            System.err.println(e.getStackTrace());
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

    public Date getDate_created() {
        return date_created;
    }

    public Date getDate_closed() {
        return date_closed;
    }

    public int getOpen() {
        return open;
    }

    public int getPriority() {
        return priority;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getUser_name() {
        return user_name;
    }

}
