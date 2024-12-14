package com.example.composer.configuration;

import com.example.composer.api.OrderServiceApi;
import com.example.composer.api.UserServiceApi;
import feign.Feign;
import feign.Logger;
import feign.form.spring.SpringFormEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(value = {
        @ComponentScan(basePackages = {
                "com.example.composer.api",
        })
})
public class FeignConfig {
    @Value(value = "${composer.services.user-service}")
    protected String userServiceUrl;
    @Value(value = "${composer.services.order-service}")
    protected String orderServiceUrl;

    @Bean
    public UserServiceApi userServiceApi() {
        return Feign.builder()
                .encoder(new SpringFormEncoder())
                .logger(new Slf4jLogger(UserServiceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(UserServiceApi.class, userServiceUrl);
    }

    @Bean
    public OrderServiceApi orderServiceApi() {
        return Feign.builder()
                .encoder(new SpringFormEncoder())
                .logger(new Slf4jLogger(UserServiceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(OrderServiceApi.class, orderServiceUrl);
    }
}
