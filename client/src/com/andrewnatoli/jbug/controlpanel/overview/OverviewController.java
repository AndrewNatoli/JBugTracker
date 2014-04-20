package com.andrewnatoli.jbug.controlpanel.overview;

import com.andrewnatoli.jbug.controlpanel.overview.uicomponents.OverviewIssueComponent;

/**
 * Created by andrew on 4/20/14.
 */
public class OverviewController extends OverviewView{
    protected OverviewModel model;
    public OverviewController() {
        model = new OverviewModel(1);
        addIssuesToView();
    }

    private void addIssuesToView() {
        for(int i=0; i<model.issues.size(); i++) {
            issueList.add(new OverviewIssueComponent(model.issues.get(i).getIssue_id()));
        }
        updateView();
    }
}
