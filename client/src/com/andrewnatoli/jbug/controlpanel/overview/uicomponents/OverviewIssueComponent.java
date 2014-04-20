package com.andrewnatoli.jbug.controlpanel.overview.uicomponents;

import com.andrewnatoli.jbug.controlpanel.issue.IssueModel;
import com.andrewnatoli.jbug.controlpanel.project.ProjectModel;

import javax.swing.*;
import java.awt.*;


public class OverviewIssueComponent extends JPanel {

    private JLabel lbl_title;
    private JButton btn_view;
    private String project_name;
    private String issue_date;

    public OverviewIssueComponent(int issue_id) {
        IssueModel issue = new IssueModel(issue_id);
        ProjectModel project = new ProjectModel(issue.getProject_id());

        setMaximumSize(new Dimension(500,75));
        setLayout(new BorderLayout());
        String title = issue.getTitle();
        if(title.length() > 80) {
            title = title.substring(0,80) + "... ";
        }
        lbl_title = new JLabel(title);
        btn_view  = new JButton("View Ticket");
        setBorder(BorderFactory.createTitledBorder(issue.getDate_created() + " - " + project.getTitle()));

        add(lbl_title, BorderLayout.CENTER);
        add(btn_view, BorderLayout.EAST);
    }
}
