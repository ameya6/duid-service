package org.duid.controller;

import lombok.extern.log4j.Log4j2;
import org.data.model.entity.SystemHealth;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Log4j2
public class HealthController {
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemHealth> create() {
        try {
            return ResponseEntity.ok(SystemHealth.builder().message("Healthy").build());
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SystemHealth.builder().message("Unhealthy " + e.getMessage()).build());
        }
    }
}
