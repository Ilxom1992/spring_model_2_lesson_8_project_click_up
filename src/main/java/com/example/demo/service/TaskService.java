package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import com.example.demo.payload.TaskDto;

import java.util.UUID;

public interface TaskService {
     ApiResponse addTask(TaskDto taskDto);

     ApiResponse editTask(Long id,TaskDto taskDto);

    //Task statusini o'zgartirish
    public ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto);

    //Task ga file biriktirish
     ApiResponse attachAFileToYourTask(TaskDto taskDto);

    //biriktirilgan file ni o'chirish
     ApiResponse deleteTheAttachedFile(Long fileId);

     ApiResponse addCommentToTask(Long taskId);

     ApiResponse addTagToTask(Long taskId);

     ApiResponse changeTag(Long tagId);

     ApiResponse deleteTag(Long tagId);

     ApiResponse assignAUserToATask(UUID userId,Long taskId);

     ApiResponse removeAUserToATask(UUID userId,Long taskId);
}
