package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.controlpanel.user.UserModel;
import javax.swing.*;
import java.awt.*;


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
    protected JLabel issueTitle;
    protected JLabel issueOpenLabel;

    /**
     * GUI elements for for the issueInfo JPanel
     */
    protected JLabel issueDescription;
    protected JLabel issuePostedBy;

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
        issueTitlePanel.setPreferredSize(new Dimension(449,50));
        issueTitle = new JLabel(formatIssueText(issue.getTitle()));
        issueTitlePanel.add(issueTitle);

        //Priority & Open/Closed status
        issueStatusPanel = new JPanel();
        issueStatusPanel.setBorder(BorderFactory.createTitledBorder("Priority: " + issue.getPriority()));
        issueStatusPanel.setPreferredSize(new Dimension(145,50));
        issueOpenLabel = new JLabel(getOpenStatus());
        issueOpenLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        issueStatusPanel.add(issueOpenLabel);
        //Add them to the top row and we're done here.
        issueTopRow.add(issueTitlePanel,BorderLayout.CENTER);
        issueTopRow.add(issueStatusPanel,BorderLayout.EAST);



        issueDescriptionPanel = new JPanel();
        issueDescriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        issueDescriptionPanel.setLayout(new BorderLayout());

        issueDescription = new JLabel(formatIssueText(issue.getDescription()) + "\n");
        issuePostedBy = new JLabel("Reported by " + author.getName());

        issueDescriptionPanel.add(issueDescription,BorderLayout.CENTER);
        issueDescriptionPanel.add(issuePostedBy,BorderLayout.SOUTH);

        //Adds the issueInfo panel to our main, IssueView JPanel
        add(issueTopRow);
        add(issueDescriptionPanel);

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
