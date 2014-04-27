package com.andrewnatoli.jbug.controlpanel;


import com.andrewnatoli.jbug.Database;
import com.andrewnatoli.jbug.authentication.CurrentUser;
import com.andrewnatoli.jbug.controlpanel.project.ProjectModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControlPanelModel {
    public ArrayList<ProjectModel> projects;

    public ControlPanelModel() {
        System.out.println("Loaded the ControlPanelModel!");
        projects = new ArrayList<ProjectModel>();

    }

    /**
     * getUserProjects
     * Fetches all of the user's projects and stores them in the projects ArrayList.
     * DOES NOT AUTOMATICALLY POPULATE A TABLE
     */
    public void fetchUserProjects() {
        int user_id = CurrentUser.getUser_id();
        projects.clear();
        System.out.println("[ControlPanelModel] Getting projects to list in control panel");
        String q = "SELECT project_id FROM jbug_projects WHERE user_id=\""+user_id+"\"";
        System.out.println("[ControlPanelModel] " + q);
        try {
            Statement getProjects = Database.conn.createStatement();
            ResultSet rs = getProjects.executeQuery(q);
            while(rs.next()) {
                System.out.println("[ControlPanelModel->getUserProjects] Adding project #" + rs.getInt("project_id"));
                projects.add(new ProjectModel(rs.getInt("project_id")));
            }
            getProjects.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading projects for control panel. " + e.getMessage());
        }
        System.out.println("[ControlPanelModel] Got 'em! Have a nice day.");
    }

    /**
     * getUserProjectCount - Gets the number of projects the user is managing with JBugTracker
     * @param user_id The user's ID we want to look up
     * @return int Number of projects
     */
    public int getUserProjectCount(int user_id) {
        try {
            int projectCount = 0;
            String q = "SELECT COUNT(*) AS count FROM jbug_projects WHERE user_id=\""+user_id+"\"";
            Statement getProjectCount = Database.conn.createStatement();
            ResultSet result = getProjectCount.executeQuery(q);
            result.next();
            projectCount = result.getInt(1);
            getProjectCount.close();
            result.close();
            return projectCount;
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("[ControlPanelModel->getUserProjectCount] " + e.getMessage());
        }
        return 0;
    }

    /**
     * getOpenIssueCount - Gets the number of open issues for projects the user manages
     * @param user_id
     * @return int Number of issues where open =1
     */
    public int getOpenIssueCount(int user_id) {
        int openIssues = 0;
        try {
            //Find the project IDs
            String q = "SELECT project_id FROM jbug_projects WHERE user_id=\""+user_id+"\"";
            Statement getProjects = Database.conn.createStatement();
            ResultSet projects = getProjects.executeQuery(q);
            while(projects.next()) {
                //Get the issue count for each project and add it to our running total
                int project_id = projects.getInt("project_id");
                String q2 = "SELECT COUNT(*) AS count FROM jbug_issues WHERE open=\"1\" AND project_id=\""+project_id+"\"";
                System.out.println(q2);
                Statement getProjectIssues = Database.conn.createStatement();
                ResultSet issues = getProjectIssues.executeQuery(q2);
                issues.next();
                int countForThisProject = issues.getInt("count");
                System.out.println("[ControlPanelModel->getOpenIssueCount] Found " + countForThisProject + " issues for project " + project_id);
                openIssues += countForThisProject;
            }
            return openIssues;
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("[ControlPanelModel->getOpenIssueCount] " + e.getMessage());
        }
        return 0;
    }

    /**
     * getResolvedIssueCount - Get the number of resolved issues for the user
     * @param user_id
     * @return int The number of issues assigned to the user's projects where open=0
     */
    public int getResolvedIssueCount(int user_id) {
        int openIssues = 0;
        try {
            //Find the project IDs
            String q = "SELECT project_id FROM jbug_projects WHERE user_id=\""+user_id+"\"";
            Statement getProjects = Database.conn.createStatement();
            ResultSet projects = getProjects.executeQuery(q);
            while(projects.next()) {
                //Get the issue count for each project and add it to our running total
                int project_id = projects.getInt("project_id");
                String q2 = "SELECT COUNT(*) AS count FROM jbug_issues WHERE open=\"0\" AND project_id=\""+project_id+"\"";
                System.out.println(q2);
                Statement getProjectIssues = Database.conn.createStatement();
                ResultSet issues = getProjectIssues.executeQuery(q2);
                issues.next();
                int countForThisProject = issues.getInt("count");
                openIssues += countForThisProject;
            }
            return openIssues;
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("[ControlPanelModel->getResolvedIssueCount] " + e.getMessage());
        }
        return 0;
    }
}
