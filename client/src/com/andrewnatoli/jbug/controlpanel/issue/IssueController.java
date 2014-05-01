package com.andrewnatoli.jbug.controlpanel.issue;

public class IssueController extends IssueView{

    /**
     * Constructor of Justice - Requires an issue_id passed to it so we know what issue to look up.
     * @param issue_id
     * TODO: Implement this style in the other packages
     */
    public IssueController(int issue_id) {
        buildGUI(issue_id);
    }

    /**
     * doUpdate()
     * Applies changes to the issue model
     */
    public void doUpdate() {
        System.out.println("[IssueView] Applying changes to model");
        issue.setTitle(issueTitle.getText().trim());
        issue.setDescription(issueDescription.getText().trim());
        issue.setOpen(issueStatus.getSelectedIndex());
        System.out.println("Open status... " + issueStatus.getSelectedIndex());
        System.out.println("[IssueView] Saving changes to database");
        issue.update();
    }
}
