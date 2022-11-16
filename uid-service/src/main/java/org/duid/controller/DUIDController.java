package org.duid.controller;

import lombok.extern.log4j.Log4j2;
import org.data.model.response.DUIDDetailedResponse;
import org.data.model.response.DUIDResponse;
import org.duid.service.DUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duid")
@Log4j2
public class DUIDController {

    @Autowired
    DUIDService duidService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DUIDResponse> create() {
        try {
            return ResponseEntity.ok(duidService.getDUID());
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            // change the response entity data
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DUIDResponse.builder().message("Issue while creating id").build());
        }
    }

    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DUIDDetailedResponse> createWithInfo() {
        try {
            return ResponseEntity.ok(duidService.getDUIDWithDetail());
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage(), e);
            // change the response entity data
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DUIDDetailedResponse.builder().message("Issue while creating id").build());
        }
    }
}
