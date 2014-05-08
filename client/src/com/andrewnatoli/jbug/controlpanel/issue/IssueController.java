package com.andrewnatoli.jbug.controlpanel.issue;

public class IssueController extends IssueView{

    /**
     * Constructor of Justice - Requires an issue_id passed to it so we know what issue to look up.
     * @param issue_id
     * TODO: Implement this style in the other packages
     */
    public IssueController(int issue_id) {
        issue = new IssueModel(issue_id);
        buildGUI(false);
    }

    /**
     * Constructor of Justice v2 - Requires a project_id. issue_id should be -1. Used for creating an issue for a project
     * This would be a lot cleaner if we were using PHP or Python >:(
     * @param issue_id MUST BE -1
     * @param project_id ID of the project we want to create the issue for
     */
    public IssueController(int issue_id,int project_id){
        if(issue_id != -1)
            System.err.println("Incorrect access by someone who edited the code >:(!!!");
        issue = new IssueModel();
        issue.setProject_id(project_id);
        buildGUI(true);
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
