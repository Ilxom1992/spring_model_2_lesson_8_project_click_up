package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Space;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProjectDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {

    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, SpaceRepository spaceRepository) {
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public List<Project> getProject() {
        return projectRepository.findAll();
    }

    @Override
    public ApiResponse addProject(ProjectDTO projectDTO) {
        final Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found", false);
        }

        Project project = new Project(
                projectDTO.getName(), optionalSpace.get(), projectDTO.isAccessType(), projectDTO.isArchived(), projectDTO.getColor()
        );
        projectRepository.save(project);
        return new ApiResponse("Project saved", true);
    }

    @Override
    public ApiResponse editProject(Long id, ProjectDTO projectDTO) {
        final Optional<Project> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            return new ApiResponse("Project not found", false);
        }
        final Project project = optionalProject.get();
        final Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found", false);
        }
        final Space space = optionalSpace.get();
        project.setArchived(projectDTO.isArchived());
        project.setColor(projectDTO.getColor());
        project.setAccessType(projectDTO.isAccessType());
        project.setName(projectDTO.getName());
        project.setSpace(space);
        return new ApiResponse("Project edited", true);
    }

    @Override
    public ApiResponse deleteProject(Long id) {
        final Optional<Project> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        projectRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
