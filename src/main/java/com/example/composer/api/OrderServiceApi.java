package com.example.composer.api;

import feign.RequestLine;

public interface OrderServiceApi {

    @RequestLine("GET /all")
    String getOrders();
}
