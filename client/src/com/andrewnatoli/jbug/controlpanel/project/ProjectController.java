package com.andrewnatoli.jbug.controlpanel.project;

import com.andrewnatoli.jbug.controlpanel.ControlPanelController;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewController;

import javax.swing.*;

public class ProjectController {

    /**
     * doUpdate()
     * @param project The project model to update
     * @param title The title for the project
     */
    public static void doUpdate(ProjectModel project, String title) {
        //If the title is blank, make them enter a legit one!
        if(title.length() == 0 || title.equals("") || title.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Enter a name for the project.");
            return;
        }
        else if(title.length() > 29)
            JOptionPane.showMessageDialog(null,"Please enter a shorter title.");
        //Update the project and return to the control panel
        else {
            project.setTitle(title);
            project.update();
            ControlPanelController.updateProjectList(); //Refresh the project list
            ControlPanelController.showOverview(); //Go back to showing the overview
        }
    }

    /**
     * doDelete()
     * @param project The project we want to delete
     */
    public static void doDelete(ProjectModel project) {
        if(project.delete()) {
            JOptionPane.showMessageDialog(null,"Project deleted.");
            ControlPanelController.updateProjectList(); //Refresh the project list
            ControlPanelController.showOverview();      //Go back to showing the overview
        }
        else
            JOptionPane.showMessageDialog(null,"Error removing project.");
    }
}
