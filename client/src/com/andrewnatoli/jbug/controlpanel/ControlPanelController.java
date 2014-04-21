package com.andrewnatoli.jbug.controlpanel;


import com.andrewnatoli.jbug.controlpanel.issue.IssueView;

public class ControlPanelController extends ControlPanelView {

    public static void showIssue(int issue_id) {
        System.out.println("Clicked on a ticket! Trying to open issue " + issue_id);
        contentPanel.removeAll();
        contentPanel.add(new IssueView(issue_id));
        controlPanelFrame.pack();
    }
}
