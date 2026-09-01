package com.famrut.farmer_management_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/v1/health")
    public String health() {
        return "Farmer Management API is running";
    }

    @GetMapping("/api/v1/message")
    public String message() {
        return "Welcome to the Farmer Management API!";
    }
}