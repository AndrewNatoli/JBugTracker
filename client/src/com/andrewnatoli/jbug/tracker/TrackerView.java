package com.andrewnatoli.jbug.tracker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Event.*;

public class TrackerView extends JFrame {

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


    public TrackerView() {
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
        contentPanel.setLayout(new GridLayout(1000,1));

        example1 = new JPanel();
        example1.setLayout(new BorderLayout());
        example1label = new JLabel("Managed to break everything in the world");
        example1btn = new JButton("View Ticket");
        example1.setBorder(BorderFactory.createTitledBorder("2014 April 20 @ 1:24am"));

        example1.add(example1label,BorderLayout.CENTER);
        example1.add(example1btn,BorderLayout.EAST);
        contentPanel.add(example1);

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
