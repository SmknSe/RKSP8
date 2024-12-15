package com.example.composer.controller;

import api.UserServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.AuthRequestDTO;
import model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-service/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceApi userServiceApi;

    @PostMapping
    public ResponseEntity<UserDTO> createOrGetUser(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Creating (or getting) user");
        var createdUser = userServiceApi.createOrGetUser(authRequestDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Fetching users");
        var users = userServiceApi.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        log.info("Fetching user by id: {}", id);
        var user = userServiceApi.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
