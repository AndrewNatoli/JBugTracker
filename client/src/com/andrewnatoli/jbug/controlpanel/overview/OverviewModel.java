package com.andrewnatoli.jbug.controlpanel.overview;

import com.andrewnatoli.jbug.Database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.andrewnatoli.jbug.authentication.CurrentUser;
import com.andrewnatoli.jbug.controlpanel.issue.IssueModel;

public class OverviewModel {

    public ArrayList<IssueModel> issues = new ArrayList<IssueModel>();

    public OverviewModel(int user_id) {
        refreshOverview();
    }

    /**
     * refreshOverview - Builds an ArrayList of issues belonging to our projets
     */
    public void refreshOverview() {
        //Wipe out the ArrayList before we fill it
        if(issues.size() > 0)
            issues.clear();

        try {
            //Get our projects
            System.out.println("Loading overview data...");
            ResultSet rs = Database.stmt.executeQuery("SELECT project_id, title FROM jbug_projects WHERE user_id=\""+ CurrentUser.getUser_id()+"\"");
            ArrayList<Integer> project_ids = new ArrayList<Integer>();
            while (rs.next()) {
                System.out.println("[OverviewModel] found project: " + rs.getString("title"));
                project_ids.add(rs.getInt("project_id"));
            }

            //Get the issues
            for(int i=0; i< project_ids.size(); i++) {
                String q = "SELECT issue_id FROM jbug_issues WHERE project_id=\""+project_ids.get(i)+"\" AND open=\"1\"";
                System.out.println(q);
                Statement issuesStatement = Database.conn.createStatement();
                ResultSet issueSet = issuesStatement.executeQuery(q);
                while(issueSet.next()) {
                    //Add the issue to our ArrayList
                    int issue_id = issueSet.getInt("issue_id");
                    issues.add(new IssueModel(issue_id));
                    System.out.println("Added issue " + issue_id);
                }
                issuesStatement.close();
            }

            project_ids.clear();
        }
        catch(SQLException e) {
            System.out.println();
            e.printStackTrace();
            System.err.println("Error getting projects for overview: " + e.getMessage());
        }
        Collections.sort(issues, new SortIssuesByDate());
        System.out.println("Done fetching overview.");
    }

    /**
     * Sort the issues ArrayList by the date they were created
     */
    public class SortIssuesByDate implements Comparator<IssueModel> {
        @Override
        public int compare(IssueModel o1, IssueModel o2) {
            System.out.println("Sorting issues by date...");
            return o1.getDate_created().compareTo(o2.getDate_created());
        }
    }
}


