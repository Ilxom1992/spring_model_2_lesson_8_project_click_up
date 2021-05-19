package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProjectDTO;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.service.ProjectService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    final ProjectService projectService;

    final SpaceRepository spaceRepository;

    public ProjectController(ProjectService projectService, SpaceRepository spaceRepository) {
        this.projectService = projectService;
        this.spaceRepository = spaceRepository;
    }

    @GetMapping
    public HttpEntity<?> getProject() {
        return ResponseEntity.ok(projectService.getProject());
    }

    @PostMapping("/addProject")
    public HttpEntity<?> addProject(@RequestBody ProjectDTO projectDTO) {
        ApiResponse response = projectService.addProject(projectDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/project/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ApiResponse response = projectService.editProject(id, projectDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/project/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = projectService.deleteProject(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}
