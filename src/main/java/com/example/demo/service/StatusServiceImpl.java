package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    final TaskRepository taskRepository;
    final StatusRepository statusRepository;
    final SpaceRepository spaceRepository;
    final ProjectRepository projectRepository;
    final CategoryRepositoryc categoryRepository;

    public StatusServiceImpl(TaskRepository taskRepository, StatusRepository statusRepository, SpaceRepository spaceRepository, ProjectRepository projectRepository, CategoryRepositoryc categoryRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.spaceRepository = spaceRepository;
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse addStatus(StatusDto statusDto, Long taskId) {
        Status status=new Status();
        status.setType(statusDto.getType());
        status.setName(status.getName());
        status.setColor(status.getColor());
        status.setCategory(categoryRepository.findById(statusDto.getCategoryId()).get());
        status.setProject(projectRepository.findById(statusDto.getProjectId()).get());
        status.setSpace(spaceRepository.findById(statusDto.getSpaceId()).get());
         statusRepository.save(status);

        Task task = taskRepository.findById(taskId).get();
        task.setStatus(status);
         taskRepository.save(task);
        return new  ApiResponse("Status saqlandi ",true);
    }
}
