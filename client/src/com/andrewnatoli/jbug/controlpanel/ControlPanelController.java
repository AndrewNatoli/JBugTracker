package com.andrewnatoli.jbug.controlpanel;

import com.andrewnatoli.jbug.authentication.CurrentUser;
import com.andrewnatoli.jbug.controlpanel.issue.IssueController;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewController;
import com.andrewnatoli.jbug.controlpanel.project.ProjectView;
import javax.swing.*;

public class ControlPanelController extends ControlPanelView {
    private static ControlPanelModel model;

    /**
     * Super constructor of justice!
     * Simply just loads projects into our projects table.
     */
    public ControlPanelController() {
        System.out.println("[ControlPanelController] Welcome to the ControlPanelController! Let's have a great time :)");
        model = new ControlPanelModel();
        updateProjectList();
        controlPanelFrame.setTitle("JBugTracker - " + CurrentUser.getEmail());
    }

    /**
     * showIssue - Hides the current view and displays an IssueView for the corresponding issue ID
     * @param issue_id int The issue ID to display
     */
    public static void showIssue(int issue_id) {
        System.out.println("Clicked on a ticket! Trying to open issue " + issue_id);
        contentPanel.removeAll();
        contentPanel.add(new IssueController(issue_id));
        controlPanelFrame.pack();
    }

    /**
     * showOverview - Hides the current view and restores the overview
     */
    public static void showOverview() {
        System.out.println("[ControlPanelController] Showing overview!");
        contentPanel.removeAll();
        contentPanel.add(new OverviewController());
        controlPanelFrame.pack();
        System.out.println("[ControlPanelController] Overview visible. Enjoy it!");
    }

    /**
     * showProject
     * Create a new project
     */
    public static void showProject() {
        System.out.println("[ControlPanelController] Showing new project editor");
        contentPanel.removeAll();
        contentPanel.add(new ProjectView());
        controlPanelFrame.pack();
        System.out.println("[ControlPanelController] There it is!");
    }

    /**
     * showProject
     * Creates a project editor view
     * @param project_id ID of the project to edit
     */
    public static void showProject(int project_id) {
        if(project_id != -1) {
            System.out.println("[ControlPanelController] Showing project editor");
            System.out.println("[ControlPanelController->showProject] Checking row " + project_id);
            contentPanel.removeAll();
            project_id = model.projects.get(project_id).getProject_id(); //Get the project ID from the row we selected
            System.out.println("[ControlPanelController->showProject] Found Project " + project_id);
            contentPanel.add(new ProjectView(project_id));
            controlPanelFrame.pack();
            System.out.println("[ControlPanelController] There it is!");
        }
        else
            JOptionPane.showMessageDialog(null,"Select a project to edit.");
    }

    /**
     * updateProjectList
     * Reloads the projects list into the model and updates the projectsTable if necessary.
     */
    public static void updateProjectList() {
        model.fetchUserProjects();
        if (model.projects.size() > 0) {
            //Update the table
            projectsTable.fireModelChange(model.projects);
            //Show the edit/view project buttons on the sidebar
            btn_editProject.setVisible(true);
            btn_viewIssues.setVisible(true);
            updateHeaderCounts();
        }
    }

    /**
     * updateHeaderCounts
     * Update the project / issue counts at the top of the window
     * This should be called by updateProjectList
     */
    public static void updateHeaderCounts() {
        projectCountLabel.setText(Integer.toString(model.getUserProjectCount(CurrentUser.getUser_id())));
        openIssueCountLabel.setText(Integer.toString(model.getOpenIssueCount(CurrentUser.getUser_id())));
        closedIssueCountLabel.setText(Integer.toString(model.getResolvedIssueCount(CurrentUser.getUser_id())));
    }

}
