package com.example.composer.services;

import com.example.composer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final AtomicLong userIdCounter = new AtomicLong();

    private final Map<String, User> userStore = new ConcurrentHashMap<>();

    @Override
    public User create(User user) {
        log.info("creating user: {}", user);
        user.setId(userIdCounter.incrementAndGet());
        userStore.put(user.getLogin(), user);
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        log.info("creating user: {}", login);
        return Optional.ofNullable(userStore.get(login));
    }
}
