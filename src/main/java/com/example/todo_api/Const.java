package com.example.todo_api;

import com.example.todo_api.model.user.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Const {

    public static boolean isEmail(String email) {
        // Regular expression pattern for matching email addresses
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher with the given email
        Matcher matcher = pattern.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }
}
