package com.example.composer.controller;

import api.OrderServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service/api")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderServiceApi orderServiceApi;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("Creating new order: {}", orderDTO);
        var createdOrder = orderServiceApi.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Getting all orders");
        return ResponseEntity.ok(orderServiceApi.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        log.info("Getting order by id: {}", id);
        return ResponseEntity.ok(orderServiceApi.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable String id) {
        log.info("Deleting order by id: {}", id);
        orderServiceApi.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
