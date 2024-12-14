package com.example.composer.services;

import com.example.composer.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    User create(User user);

    Optional<User> findByLogin(String login);
}
