package com.example.carsharing.controller;

import com.carsharing.controller.HealthCheckController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class HealthCheckControllerTest {
    private HealthCheckController healthCheckController;
    private ResponseEntity<String> responseEntity;
    @Test
    void healthCheck_ok() {
        healthCheckController = new HealthCheckController();
        responseEntity = healthCheckController.healthCheck();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("Running successfully", responseEntity.getBody());
    }
}
