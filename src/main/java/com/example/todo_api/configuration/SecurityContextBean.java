package com.example.todo_api.configuration;

import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.user.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextBean {

    public static AppUser getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AppUser) authentication.getPrincipal();
    }
}

