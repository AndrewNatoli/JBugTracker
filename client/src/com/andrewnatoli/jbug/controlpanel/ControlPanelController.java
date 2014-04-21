package com.andrewnatoli.jbug.controlpanel;


import com.andrewnatoli.jbug.controlpanel.issue.IssueView;

public class ControlPanelController extends ControlPanelView {
    private ControlPanelModel model;

    /**
     * Super constructor of justice!
     * Simply just loads projects into our projects table.
     */
    public ControlPanelController() {
        System.out.println("[ControlPanelController] Welcome to the ControlPanelController! Let's have a great time :)");
        model = new ControlPanelModel();
        updateProjectList();
    }

    /**
     * showIssue - Hides the current view and displays an IssueView for the corresponding issue ID
     * @param issue_id int The issue ID to display
     */
    public static void showIssue(int issue_id) {
        System.out.println("Clicked on a ticket! Trying to open issue " + issue_id);
        contentPanel.removeAll();
        contentPanel.add(new IssueView(issue_id));
        controlPanelFrame.pack();
    }

    /**
     * updateProjectList
     * Reloads the projects list into the model and updates the projectsTable if neccessarry.
     */
    private void updateProjectList() {
        model.fetchUserProjects();
        if (model.projects.size() > 0)
            projectsTable.fireModelChange(model.projects);
    }
}
