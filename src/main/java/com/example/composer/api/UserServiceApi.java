package com.example.composer.api;

import feign.RequestLine;

public interface UserServiceApi {

    @RequestLine("GET /all")
    String getUsers();
}
