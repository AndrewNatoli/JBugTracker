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
}
