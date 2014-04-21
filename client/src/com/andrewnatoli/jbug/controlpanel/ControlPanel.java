package com.andrewnatoli.jbug.controlpanel;

import com.andrewnatoli.jbug.controlpanel.issue.IssueView;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewController;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewView;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {

    public  static JFrame controlPanelFrame;
    private static JPanel mainPanel;
    private static JPanel sidePanel;

    private static JPanel statsPanel;
    private static JPanel projectCountPanel;
    private static JPanel openIssueCountPanel;
    private static JPanel closedIssueCountPanel;
    private static JPanel contentPanel;

    private static JLabel projectCountLabel;
    private static JLabel openIssueCountLabel;
    private static JLabel closedIssueCountLabel;

    private static JLabel logo;

    OverviewView overviewController;


    public ControlPanel() {
        controlPanelFrame = new JFrame();
        controlPanelFrame.setTitle("JBugTracker");
        controlPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlPanelFrame.setSize(900,600);
        controlPanelFrame.setPreferredSize(new Dimension(900,600));
        controlPanelFrame.setLayout(new BorderLayout());
        controlPanelFrame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1,3));

        projectCountPanel = new JPanel();
        projectCountPanel.setBorder(BorderFactory.createTitledBorder("Projects you Manage"));
        projectCountLabel = new JLabel("3");
        projectCountLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 26));
        projectCountLabel.setForeground(Color.BLACK);
        projectCountPanel.add(projectCountLabel);

        openIssueCountPanel = new JPanel();
        openIssueCountPanel.setBorder(BorderFactory.createTitledBorder("Open Issues"));
        openIssueCountLabel = new JLabel("142");
        openIssueCountLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 26));
        openIssueCountLabel.setForeground(Color.RED);
        openIssueCountPanel.add(openIssueCountLabel);

        closedIssueCountPanel = new JPanel();
        closedIssueCountPanel.setBorder(BorderFactory.createTitledBorder("Issues Resolved"));
        closedIssueCountLabel = new JLabel("6073");
        closedIssueCountLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 26));
        closedIssueCountLabel.setForeground(Color.GREEN);
        closedIssueCountPanel.add(closedIssueCountLabel);

        statsPanel.add(projectCountPanel);
        statsPanel.add(openIssueCountPanel);
        statsPanel.add(closedIssueCountPanel);

        contentPanel = new JPanel();

        overviewController = new OverviewController();
        contentPanel.add(overviewController);

        JScrollPane contentArea = new JScrollPane(contentPanel);
        contentArea.setPreferredSize(new Dimension(700,300));

        mainPanel.add(statsPanel,BorderLayout.NORTH);
        mainPanel.add(contentArea,BorderLayout.CENTER);

        sidePanel = new JPanel();
        logo = new JLabel(new ImageIcon("controlpanellogo.png"));
        sidePanel.add(logo);

        controlPanelFrame.getContentPane().add(mainPanel,BorderLayout.CENTER);
        controlPanelFrame.getContentPane().add(sidePanel,BorderLayout.EAST);
        //pack();

        controlPanelFrame.setVisible(true);
    }


    public static void showIssue(int issue_id) {
        System.out.println("Clicked on a ticket! Trying to open issue " + issue_id);
        contentPanel.removeAll();
        contentPanel.add(new IssueView(issue_id));
        controlPanelFrame.pack();
    }

}
