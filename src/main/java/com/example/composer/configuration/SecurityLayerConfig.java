package com.example.composer.configuration;

import com.example.composer.services.UserService;
import com.example.composer.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityLayerConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
