package com.andrewnatoli.jbug.authentication;

import com.andrewnatoli.jbug.controlpanel.user.UserModel;

import java.util.Date;

/**
 * Class to store the user we're currently logged in as.
 */
public class CurrentUser {

    public CurrentUser() {
        System.err.println("Why are you trying to create an instance of this session object? >:(");
        System.exit(0);
    }

    private static int     user_id = -1;
    private static String  name;
    private static String  email;
    private static Date date_created;

    public static void setCurrentUser(UserModel user) {
        user_id     = user.getUser_id();
        name        = user.getName();
        email       = user.getEmail();
        date_created= user.getDate_created();
    }

    public static boolean isLoggedIn() {
        if(user_id != -1)
            return true;
        else
            return false;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static Date getDate_created() {
        return date_created;
    }
}
