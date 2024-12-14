package com.example.composer.controller;

import api.UserServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceApi userServiceApi;

    @GetMapping("/all")
    public String testRedirect(){
        log.info("Fetching users");

        return "userServiceApi.getUsers()";
    }
}
