package com.example.demo.service;

import com.example.demo.payload.*;

import java.util.UUID;

public interface TaskService {

     ApiResponse addTask(TaskDto taskDto);

     ApiResponse editTask(Long id,TaskDto taskDto);

     ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto);//Task statusini o'zgartirish

     ApiResponse attachAFileToYourTask(TaskDto taskDto); //Task ga file biriktirish


     ApiResponse deleteTheAttachedFile(Long fileId);//biriktirilgan file ni o'chirish

     ApiResponse addCommentToTask(Long taskId, CommentDto commentDto);

     ApiResponse addTagToTask(Long taskId, TagDto tagDto);

     ApiResponse changeTag(Long tagId,TagDto tagDto);

     ApiResponse deleteTag(Long tagId);

     ApiResponse assignAUserToATask(UUID userId,Long taskId);

     ApiResponse removeAUserToATask(UUID userId,Long taskId);
}
