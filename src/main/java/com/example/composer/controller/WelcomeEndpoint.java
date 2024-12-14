package com.example.composer.controller;

import com.example.composer.model.AuthUser;
import com.example.composer.model.User;
import com.example.composer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeEndpoint {

    @Autowired
    private UserService userService;

    @GetMapping
    public String welcome() {
        User currentUser = getCurrentUser();
        return String.format("Welcome, %s! (user id: %s, user login: %s)",
                currentUser.getName(), currentUser.getId(), currentUser.getLogin());
    }

    public User getCurrentUser() {
        return userService.findByLogin(getAuthenticatedUser().getName()).orElseThrow(() -> new RuntimeException("No user logged in."));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private AuthUser getAuthenticatedUser() {
        return (AuthUser) getAuthentication().getPrincipal();
    }
}
