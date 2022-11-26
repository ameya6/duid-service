package org.duid.controller;

import lombok.extern.log4j.Log4j2;
import org.data.dao.SystemHealthRepository;
import org.data.model.entity.SystemHealth;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SystemHealthRepository systemHealthRepository;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemHealth> create() {
        SystemHealth systemHealth = null;
        try {
            systemHealth = SystemHealth.builder().message("Healthy").build();
            return ResponseEntity.ok(systemHealth);
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            systemHealth = SystemHealth.builder().message("Unhealthy " + e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(systemHealth);
        } finally {
            systemHealthRepository.save(systemHealth).doOnError(error -> log.error(error)).subscribe();
        }
    }
}
