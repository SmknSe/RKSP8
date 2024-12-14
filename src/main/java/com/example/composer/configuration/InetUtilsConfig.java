package com.example.composer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;

@Configuration
public class InetUtilsConfig {

    @Bean
    public InetUtils inetUtils() {
        return new InetUtils(new InetUtilsProperties());
    }
}
