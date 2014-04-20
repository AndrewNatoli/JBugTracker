package com.andrewnatoli.jbug.controlpanel.overview;

import com.andrewnatoli.jbug.controlpanel.overview.uicomponents.OverviewIssueComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.util.ArrayList;


public class OverviewView extends JPanel {

    protected ArrayList<OverviewIssueComponent> issueList = new ArrayList<OverviewIssueComponent>();

    public OverviewView() {
        setLayout(new GridLayout(1,1));
    }

    /**
     * Updates the list of OverviewIssueComponents
     */
    protected void updateView() {
        removeAll(); //Remove all elements from the JPanel)
        if(issueList.size() > 0) {
            setLayout(new GridLayout(issueList.size(),1));
            for(int i=0; i<issueList.size(); i++) {
                System.out.println("Adding issue " + i + " to OverviewView");
                add(issueList.get(i));
            }
        }
        else {
            System.out.println("No issues to add to the OverviewView.");
            setLayout(new GridLayout(1,1));
        }
    }
}
