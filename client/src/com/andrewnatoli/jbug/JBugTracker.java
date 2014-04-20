package com.andrewnatoli.jbug;

import com.andrewnatoli.jbug.authentication.MainMenuView;
/**
 * Created by andrew on 4/19/14.
 */
public class JBugTracker {

    public static void main(String[] args) {
        System.out.println("Starting JBugTracker!");
        Configuration.loadConfiguration();
        new MainMenuView();
    }

}
