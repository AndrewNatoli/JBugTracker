package com.andrewnatoli.jbug.controlpanel.overview.uicomponents;

import com.andrewnatoli.jbug.controlpanel.issue.IssueModel;

import javax.swing.*;
import java.awt.*;


public class OverviewIssueComponent extends JPanel {

    private JLabel lbl_title;
    private JButton btn_view;
    private String project_name;
    private String issue_date;

    public OverviewIssueComponent(int issue_id) {
        IssueModel issue = new IssueModel(issue_id);
        setMinimumSize(new Dimension(700,50));
        setLayout(new BorderLayout());
        lbl_title = new JLabel(issue.getTitle());
        btn_view  = new JButton("View Ticket");
        setBorder(BorderFactory.createTitledBorder(issue.getDate_created() + " "));

        add(lbl_title, BorderLayout.CENTER);
        add(btn_view, BorderLayout.EAST);
    }
}
