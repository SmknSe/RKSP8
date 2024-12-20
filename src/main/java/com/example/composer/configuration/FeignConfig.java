package com.example.composer.configuration;

import api.ItemServiceApi;
import api.OrderServiceApi;
import api.UserServiceApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

@Configuration
@ComponentScans(value = {
        @ComponentScan(basePackages = {
                "api",
        })
})
public class FeignConfig {
    @Value(value = "${composer.services.user-service}")
    protected String userServiceUrl;
    @Value(value = "${composer.services.order-service}")
    protected String orderServiceUrl;
    @Value(value = "${composer.services.item-service}")
    protected String itemServiceUrl;

    @Bean
    public UserServiceApi userServiceApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(new ObjectMapper()))
                .decoder(new JacksonDecoder(new ObjectMapper()))
                .logger(new Slf4jLogger(UserServiceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(UserServiceApi.class, userServiceUrl);
    }

    @Bean
    public OrderServiceApi orderServiceApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(new ObjectMapper()))
                .decoder(new JacksonDecoder(new ObjectMapper()))
                .logger(new Slf4jLogger(UserServiceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(OrderServiceApi.class, orderServiceUrl);
    }

    @Bean
    public ItemServiceApi itemServiceApi() {
        return Feign.builder()
                .encoder(new JacksonEncoder(new ObjectMapper()))
                .decoder(new JacksonDecoder(new ObjectMapper()))
                .logger(new Slf4jLogger(UserServiceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(ItemServiceApi.class, itemServiceUrl);
    }
}
