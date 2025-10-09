package com.example.springbootapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot is running!";
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Spring Boot API!";
    }
}
