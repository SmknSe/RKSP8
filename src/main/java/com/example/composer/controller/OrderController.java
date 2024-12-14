package com.example.composer.controller;

import com.example.composer.api.OrderServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderServiceApi orderServiceApi;

    @GetMapping("/all")
    public String getAll(){
        log.info("Fetching orders");
        return "orderServiceApi.getOrders()";
    }
}
