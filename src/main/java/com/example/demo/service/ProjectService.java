package com.example.demo.service;


import com.example.demo.entity.Project;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<Project> getProject();

    ApiResponse addProject(ProjectDTO projectDTO);

    ApiResponse editProject(Long id, ProjectDTO projectDTO);

    ApiResponse deleteProject(Long id);
}
