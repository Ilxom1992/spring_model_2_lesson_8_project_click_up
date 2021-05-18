package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import com.example.demo.payload.TaskDto;
import com.example.demo.repository.CategoryRepositoryc;
import com.example.demo.repository.PriorityRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.TaskRepository;


import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService{
    final TaskRepository taskRepository;
    final StatusRepository statusRepository;
    final CategoryRepositoryc categoryRepositoryc;
    final PriorityRepository priorityRepository;

    public TaskServiceImp(TaskRepository taskRepository, StatusRepository statusRepository, CategoryRepositoryc categoryRepositoryc, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.categoryRepositoryc = categoryRepositoryc;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public ApiResponse addTask(TaskDto taskDto) {
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDto.getStatusId());
        task.setStatus(optionalStatus.get());
        return null;
    }

    @Override
    public ApiResponse editTask(Long id, TaskDto taskDto) {
        return null;
    }

    @Override
    public ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto) {
        return null;
    }

    @Override
    public ApiResponse attachAFileToYourTask(TaskDto taskDto) {
        return null;
    }

    @Override
    public ApiResponse deleteTheAttachedFile(Long fileId) {
        return null;
    }

    @Override
    public ApiResponse addCommentToTask() {
        return null;
    }

    @Override
    public ApiResponse addTagToTask() {
        return null;
    }

    @Override
    public ApiResponse changeTag() {
        return null;
    }

    @Override
    public ApiResponse deleteTag() {
        return null;
    }

    @Override
    public ApiResponse assignAUserToATask() {
        return null;
    }

    @Override
    public ApiResponse removeAUserToATask() {
        return null;
    }
}
