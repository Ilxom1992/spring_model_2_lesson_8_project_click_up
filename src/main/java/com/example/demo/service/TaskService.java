package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import com.example.demo.payload.TaskDto;

public interface TaskService {
    public ApiResponse addTask(TaskDto taskDto);

    public ApiResponse editTask(Long id,TaskDto taskDto);

    //Task statusini o'zgartirish
    public ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto);

    //Task ga file biriktirish
    public ApiResponse attachAFileToYourTask(TaskDto taskDto);

    //biriktirilgan file ni o'chirish
    public ApiResponse deleteTheAttachedFile(Long fileId);

    public ApiResponse addCommentToTask();

    public ApiResponse addTagToTask();

    public ApiResponse changeTag();

    public ApiResponse deleteTag();

    public ApiResponse assignAUserToATask();

    public ApiResponse removeAUserToATask();
}
