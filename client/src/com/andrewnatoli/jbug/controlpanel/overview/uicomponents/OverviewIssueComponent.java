package com.andrewnatoli.jbug.controlpanel.overview.uicomponents;

import com.andrewnatoli.jbug.controlpanel.ControlPanelController;
import com.andrewnatoli.jbug.controlpanel.issue.IssueModel;
import com.andrewnatoli.jbug.controlpanel.project.ProjectModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OverviewIssueComponent extends JPanel {

    private JLabel lbl_title;
    private JButton btn_view;
    private String project_name;
    private String issue_date;

    public OverviewIssueComponent(int issue_id) {
        final IssueModel issue = new IssueModel(issue_id);
        ProjectModel project = new ProjectModel(issue.getProject_id());
        setMaximumSize(new Dimension(500, 75));
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(issue.getDate_created() + " - " + project.getTitle()));
        String title = issue.getTitle();
        if(title.length() > 80) {
            title = title.substring(0,80) + "... ";
        }
        lbl_title = new JLabel(title);

        /**
         * JButton to view the issue
         */
        btn_view  = new JButton("View Ticket");
        btn_view.addActionListener(new ActionListener() {
            /**
             * Tell the OverviewController to show the IssueView for the ticket we clicked on.
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ControlPanelController.showIssue(issue.getIssue_id());
            }
        });

        add(lbl_title, BorderLayout.CENTER);
        add(btn_view, BorderLayout.EAST);
    }
}
