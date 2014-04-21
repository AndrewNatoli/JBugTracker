package com.andrewnatoli.jbug.controlpanel;

import com.andrewnatoli.jbug.controlpanel.issue.IssueView;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewController;
import com.andrewnatoli.jbug.controlpanel.overview.OverviewView;
import com.andrewnatoli.jbug.controlpanel.uicomponents.ProjectListTable;

import javax.swing.*;
import java.awt.*;

public class ControlPanelView {

    public  static JFrame controlPanelFrame;
    protected static JPanel mainPanel;
    protected static JPanel sidePanel;

    protected static JPanel statsPanel;
    protected static JPanel projectCountPanel;
    protected static JPanel openIssueCountPanel;
    protected static JPanel closedIssueCountPanel;
    protected static JPanel contentPanel;

    protected static JLabel projectCountLabel;
    protected static JLabel openIssueCountLabel;
    protected static JLabel closedIssueCountLabel;

    protected static JLabel logo;
    protected static ProjectListTable projectsTable;

    protected OverviewView overviewController;


    public ControlPanelView() {
        controlPanelFrame = new JFrame();
        controlPanelFrame.setTitle("JBugTracker");
        controlPanelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlPanelFrame.setSize(900,600);
        controlPanelFrame.setPreferredSize(new Dimension(900, 600));
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
        contentArea.setPreferredSize(new Dimension(700, 300));

        mainPanel.add(statsPanel,BorderLayout.NORTH);
        mainPanel.add(contentArea,BorderLayout.CENTER);

        //Create the side bar
        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        logo = new JLabel(new ImageIcon("controlpanellogo.png"));
        sidePanel.add(logo,BorderLayout.NORTH);

        projectsTable = new ProjectListTable();
        JTable table = new JTable(projectsTable);
        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableOverflow = new JScrollPane(table);
        sidePanel.add(tableOverflow,BorderLayout.CENTER);

        controlPanelFrame.getContentPane().add(mainPanel,BorderLayout.CENTER);
        controlPanelFrame.getContentPane().add(sidePanel,BorderLayout.EAST);
        //pack();

        controlPanelFrame.setVisible(true);
    }
}