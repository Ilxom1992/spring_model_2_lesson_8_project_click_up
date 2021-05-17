package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.TaskDto;

public interface TaskService {
    public ApiResponse addTask(TaskDto taskDto);
    public ApiResponse editTask(TaskDto taskDto);
    public ApiResponse editStatusTask(TaskDto taskDto);
    public ApiResponse addFileToTask(TaskDto taskDto);
    public ApiResponse deleteFileToTask(Long fileId);
    public ApiResponse addCommentToTask();
    public ApiResponse addTagToTask();
    public ApiResponse EditTagToTask();
    public ApiResponse deleteTagToTask();
    public ApiResponse taskToUserAssign();
    public ApiResponse removeTaskToUserAssign();
}
