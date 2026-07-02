package com.esra.pos.controller;

import com.esra.pos.model.LogActivity;
import com.esra.pos.service.LogActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class LogActivityController {

    private final LogActivityService logActivityService;

    public LogActivityController(LogActivityService logActivityService) {
        this.logActivityService = logActivityService;
    }

    @GetMapping
    public ResponseEntity<List<LogActivity>> getAllLogs() {
        return ResponseEntity.ok(logActivityService.getAllLogs());
    }

    @PostMapping
    public ResponseEntity<LogActivity> createLog(@RequestBody LogActivity logActivity) {
        return ResponseEntity.ok(logActivityService.createLog(logActivity));
    }
}