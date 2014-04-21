package com.andrewnatoli.jbug.controlpanel.issue;

import com.andrewnatoli.jbug.controlpanel.user.UserModel;
import javax.swing.*;
import javax.swing.border.Border;
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
    protected JPanel issueInfo;

    /**
     * GUI elements for for the issueInfo JPanel
     */
    protected JLabel issueTitle;
    protected JLabel issueDescription;
    protected JLabel issuePostedBy;
    protected JLabel issueOpenPriority;

    /**
     * Constructor of Justice - Requires an issue_id passed to it so we know what issue to look up.
     * @param issue_id
     */
    public IssueView(int issue_id) {
        System.out.println("Preparing issue view");
        issue = new IssueModel(issue_id);
        author = new UserModel(issue.getUser_id());
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(600,300));

        issueInfo = new JPanel();
        issueInfo.setBorder(BorderFactory.createTitledBorder("Issue #" + issue.getIssue_id()));
        issueInfo.setLayout(new GridLayout(4,1));

        issuePostedBy = new JLabel("Reported by " + author.getName());
        issueOpenPriority = new JLabel("Status: " + getOpenStatus() + " - Priority: " + issue.getPriority());
        issueTitle = new JLabel(formatIssueText(issue.getTitle()));
        issueDescription = new JLabel("\n" + formatIssueText(issue.getDescription()));

        issueInfo.add(issueTitle);
        issueInfo.add(issuePostedBy);
        issueInfo.add(issueDescription);


        //Adds the issueInfo panel to our main, IssueView JPanel
        add(issueInfo);

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
