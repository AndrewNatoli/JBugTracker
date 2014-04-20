package com.andrewnatoli.jbug.controlpanel;

import com.andrewnatoli.jbug.controlpanel.overview.OverviewController;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewView;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {

    JPanel mainPanel;
    JPanel sidePanel;

    JPanel statsPanel;
    JPanel projectCountPanel;
    JPanel openIssueCountPanel;
    JPanel closedIssueCountPanel;
    JPanel contentPanel;

    JLabel projectCountLabel;
    JLabel openIssueCountLabel;
    JLabel closedIssueCountLabel;

    JLabel logo;

    JPanel example1;
    JLabel example1label;
    JButton example1btn;

    OverviewView overviewController;


    public ControlPanel() {
        setTitle("JBugTracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setLayout(new BorderLayout());
        setResizable(false);

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

        mainPanel.add(statsPanel,BorderLayout.NORTH);
        mainPanel.add(contentArea,BorderLayout.CENTER);

        sidePanel = new JPanel();
        logo = new JLabel(new ImageIcon("controlpanellogo.png"));
        sidePanel.add(logo);

        getContentPane().add(mainPanel,BorderLayout.CENTER);
        getContentPane().add(sidePanel,BorderLayout.EAST);
        //pack();




        setVisible(true);
    }

}
