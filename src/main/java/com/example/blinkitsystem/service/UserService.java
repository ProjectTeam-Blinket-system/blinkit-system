package com.example.blinkitsystem.service;

import com.example.blinkitsystem.model.User;

public class UserService {

    private static User currentUser;

    public static void login(User user) {
        currentUser = user;
        user.login(); // your existing method
    }

    public static void logout() {
        if (currentUser != null) {
            currentUser.logout();
        }
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}