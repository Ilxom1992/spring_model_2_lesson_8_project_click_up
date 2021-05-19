package com.example.demo.service;

import com.example.demo.payload.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.UUID;

public interface TaskService {

     ApiResponse addTask(TaskDto taskDto);

     ApiResponse editTask(Long id,TaskDto taskDto);

     ApiResponse changeYourTaskStatus(Long statusId, StatusDto statusDto);//Task statusini o'zgartirish

     ApiResponse attachAFileToYourTask(MultipartHttpServletRequest request, TaskAttachmentDTO taskAttachmentDTO) throws IOException; //Task ga file biriktirish


     ApiResponse deleteTheAttachedFile(Long taskId,UUID attachmentId);//biriktirilgan file ni o'chirish

     ApiResponse addCommentToTask(Long taskId, CommentDto commentDto);

     ApiResponse addTagToTask(Long taskId, TagDto tagDto);

     ApiResponse changeTag(Long tagId,TagDto tagDto);

     ApiResponse deleteTag(Long tagId);

     ApiResponse assignAUserToATask(UUID userId,Long taskId);

     ApiResponse removeAUserToATask(UUID userId,Long taskId);
}
