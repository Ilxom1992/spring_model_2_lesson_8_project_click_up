package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.payload.*;
import com.example.demo.repository.*;


import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    final TaskRepository taskRepository;
    final StatusRepository statusRepository;
    final CategoryRepositoryc categoryRepositoryc;
    final PriorityRepository priorityRepository;
    final CommitRepository commitRepository;
    final WorkspaceRepository workspaceRepository;
    final TagRepository tagRepository;

    public TaskServiceImp(TaskRepository taskRepository, StatusRepository statusRepository, CategoryRepositoryc categoryRepositoryc, PriorityRepository priorityRepository, CommitRepository commitRepository, WorkspaceRepository workspaceRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.categoryRepositoryc = categoryRepositoryc;
        this.priorityRepository = priorityRepository;
        this.commitRepository = commitRepository;
        this.workspaceRepository = workspaceRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ApiResponse addTask(TaskDto taskDto) {
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDto.getStatusId());
        task.setStatus(optionalStatus.get());
        Task save = taskRepository.save(task);
        return new  ApiResponse("Task saqlandi",true,save);
    }

    @Override
    public ApiResponse editTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).get();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Status> optionalStatus = statusRepository.findById(taskDto.getStatusId());
        task.setStatus(optionalStatus.get());
        Task save = taskRepository.save(task);
        return new  ApiResponse("Task saqlandi",true,save);

    }

    @Override
    public ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto) {
        Optional<Status> optionalStatus = statusRepository.findById(statusId);
        Status status = optionalStatus.get();
        status.setColor(statusDto.getColor());
        status.setName(statusDto.getName());
        status.setType(statusDto.getType());
        statusRepository.save(status);
        return new  ApiResponse("Task statusi o'zgartirildi",true);
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
    public ApiResponse addCommentToTask(Long taskId, CommentDto commentDto) {
        Task task = taskRepository.findById(taskId).get();
        Comment comment=new Comment();
        comment.setName(comment.getName());
        comment.setTask(task);
        commitRepository.save(comment);
        return new  ApiResponse("Commit Taskga Biriktirildi",true);
    }

    @Override
    public ApiResponse addTagToTask(Long workSpaceId, TagDto tagDto) {

        Workspace workspace = workspaceRepository.findById(workSpaceId).get();
        Tag tag=new Tag();
        tag.setColor(tag.getColor());
        tag.setName(tagDto.getName());
        tag.setWorkspace(workspace);
        tagRepository.save(tag);
        return new  ApiResponse("Tag biriktirildi",true);
    }

    @Override
    public ApiResponse changeTag(Long tagId) {
        return null;
    }

    @Override
    public ApiResponse deleteTag(Long tagId) {
        return null;
    }

    @Override
    public ApiResponse assignAUserToATask(UUID userId, Long taskId) {
        return null;
    }

    @Override
    public ApiResponse removeAUserToATask(UUID userId, Long taskId) {
        return null;
    }
}
