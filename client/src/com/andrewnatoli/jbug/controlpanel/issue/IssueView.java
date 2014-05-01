package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.authentication.CurrentUser;
import com.andrewnatoli.jbug.controlpanel.ControlPanelView;
import com.andrewnatoli.jbug.controlpanel.user.UserModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IssueView extends JPanel {
    /**
     * issue - Houses data for the issue
     */
    protected IssueModel  issue;

    /**
     * author - Houses data for the issue's author
     */
    protected UserModel   author;

    /**
     * A panel showing off the issue's information
     */
    protected JPanel issueDescriptionPanel;

    /**
     * GUI Elements for the top panel
     */
    protected JPanel issueTopRow;
    protected JPanel issueTitlePanel;
    protected JPanel issueStatusPanel;
    protected JTextArea issueTitle;
    protected JLabel issueOpenLabel;

    /**
     * GUI elements for for the issueInfo JPanel
     */
    protected JTextArea issueDescription;
    protected JLabel issuePostedBy;

    /**
     * Combo-boxes and their strings
     */
    protected JComboBox issueStatus;
    protected JComboBox priorityLevel;
    String[] issueStatusStrings = { "Closed","Open" };
    String[] issuePriorityStrings = { "0 - Feature Request","1 - Low Priority","2","3","4","5","6","7","8","9 - Urgent"};

    /**
     * Button to revise the ticket
     */
    protected JButton btn_reviseTicket;
    protected JButton btn_saveChanges;

    /**
     * Allow the description editor to be scrollable
     */
    JScrollPane issueDescriptionScroller;

    /**
     * Constructor of Justice - Requires an issue_id passed to it so we know what issue to look up.
     * @param issue_id
     */
    public IssueView(int issue_id) {
        System.out.println("Preparing issue view");
        issue = new IssueModel(issue_id);
        author = new UserModel(issue.getUser_id());
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(600, 300));

        /*
            Top row elements
         */
        issueTopRow = new JPanel();
        issueTopRow.setLayout(new BorderLayout());
        issueTopRow.setPreferredSize(new Dimension(600,75));

        //Title Panel
        issueTitlePanel = new JPanel();
        issueTitlePanel.setBorder(BorderFactory.createTitledBorder("Issue #" + issue.getIssue_id()));
        issueTitlePanel.setPreferredSize(new Dimension(449, 50));
        issueTitle = new JTextArea();
        issueTitle.setEditable(false);
        issueTitle.setOpaque(false);
        issueTitle.setText(issue.getTitle());
        issueTitlePanel.add(issueTitle);

        //Status LABEL
        issueStatusPanel = new JPanel();
        issueStatusPanel.setBorder(BorderFactory.createTitledBorder("Priority: " + issue.getPriority()));
        issueStatusPanel.setPreferredSize(new Dimension(145, 50));
        issueOpenLabel = new JLabel(getOpenStatus());
        issueOpenLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        //Status EDITOR
        issueStatus = new JComboBox(issueStatusStrings);
        issueStatus.setOpaque(false);
        issueStatus.setVisible(false);
        issueStatus.setSelectedIndex(issue.getOpen());
        issueStatus.setEditable(false);

        issueStatusPanel.add(issueOpenLabel); //Switch it out for issueStatus combobox when we edit the issue
        issueStatusPanel.add(issueStatus);
        //Add them to the top row and we're done here.
        issueTopRow.add(issueTitlePanel,BorderLayout.CENTER);
        issueTopRow.add(issueStatusPanel,BorderLayout.EAST);


        //Issue Description
        issueDescriptionPanel = new JPanel();
        issueDescriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        issueDescriptionPanel.setLayout(new BorderLayout());

        issueDescription = new JTextArea();
        issueDescription.setEditable(false);
        issueDescription.setOpaque(false);
        issueDescription.setLineWrap(true);
        issueDescription.setWrapStyleWord(true);
        issueDescription.setLineWrap(true);
        issueDescription.setAutoscrolls(true);
        issueDescription.setText(issue.getDescription());
        issueDescriptionScroller = new JScrollPane(issueDescription);
        issueDescriptionScroller.setOpaque(false);

        issuePostedBy = new JLabel("Reported by " + author.getName());

        issueDescriptionPanel.add(issueDescriptionScroller,BorderLayout.CENTER);
        issueDescriptionPanel.add(issuePostedBy,BorderLayout.SOUTH);

        //Adds the issueInfo panel to our main, IssueView JPanel
        add(issueTopRow);
        add(issueDescriptionPanel);

        /**
         * "Revise Ticket" button & "Save Changes" button
         * Displays if the issue was posted by the user that is currently viewing it
         */
        if(issue.getUser_id() == CurrentUser.getUser_id()) {
            btn_reviseTicket = new JButton("Revise Ticket");
            btn_saveChanges  = new JButton("Save Changes");

            //"Revise ticket" handler
            btn_reviseTicket.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Clicked button to revise ticket");
                    remove(btn_reviseTicket);
                    add(btn_saveChanges);

                    issueDescriptionScroller.setOpaque(true);
                    issueDescription.setEditable(true);
                    issueTitle.setOpaque(true);
                    issueTitle.setEditable(true);

                    issueStatus.setVisible(true);
                    issueStatus.setEditable(true);
                    issueOpenLabel.setVisible(false);

                    ControlPanelView.controlPanelFrame.pack();
                }
            });

            //"Save Changes" handler
            btn_saveChanges.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("[IssueView] Clicked save changes button.");
                    System.out.println("[IssueView] Updating GUI Components");
                    remove(btn_saveChanges);
                    add(btn_reviseTicket);

                    issueDescriptionScroller.setOpaque(false);
                    issueDescription.setEditable(false);
                    issueTitle.setEditable(false);
                    issueTitle.setOpaque(false);
                    issueStatus.setVisible(false);
                    issueStatus.setEditable(false);
                    issueOpenLabel.setVisible(true);

                    System.out.println("[IssueView] Applying changes to model");
                    issue.setTitle(issueTitle.getText().trim());
                    issue.setDescription(issueDescription.getText().trim());
                    issue.setOpen(issueStatus.getSelectedIndex());

                    System.out.println("[IssueView] Saving changes to database");
                    issue.update();

                    ControlPanelView.controlPanelFrame.pack();
                }
            });


            //Add the reviseTicket button to the GUI
            add(btn_reviseTicket);
        }

        System.out.println("Boom, IssueView is visible. Enjoy it.");
    }

    /**
     * getOpenStatus - Returns "open" or "closed" pending on the status of the issue
     * @return String Closed/Open (for 0/1)
     */
    public String getOpenStatus() {
        if(issue.getOpen() == 0)
            return "Closed";
        else
            return "Open";
    }

    /**
     * formatIssueText
     * Adds line breaks to the text, text and spits out the result.
     * @param text The text to parse
     * @return String The formatted text.
     */
    public String formatIssueText(String text) {
        String output = "";
        if(text.length() > 70) {
            for(int i=0; i<text.length(); i+=70) {
                output = output + text.substring(i,i+70) + "\n";
            }
        }
        else
            output = text;
        return output;
    }

}
