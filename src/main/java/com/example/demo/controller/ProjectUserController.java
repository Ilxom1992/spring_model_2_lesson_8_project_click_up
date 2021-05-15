package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.service.ProjectUserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequestMapping("/projectUser")
public class ProjectUserController {

    final ProjectUserService projectUserRepository;

    public ProjectUserController(ProjectUserService projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }


    @PostMapping("/add")
    public HttpEntity<?> add(@RequestParam UUID memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.addMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/remove")
    public HttpEntity<?> remove(@RequestParam UUID memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.removeMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
