package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import com.example.demo.service.StatusService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {
    final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/addStatus/{taskId}")
    public HttpEntity<?> addStatus(@RequestBody StatusDto statusDto,@PathVariable Long taskId) {
        ApiResponse response = statusService.addStatus(statusDto,taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
