package com.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    @Operation(summary = "Health check controller")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Running successfully");
    }
}
