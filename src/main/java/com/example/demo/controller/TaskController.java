package com.example.demo.controller;

import com.example.demo.entity.Status;
import com.example.demo.payload.*;
import com.example.demo.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addTask")
    public HttpEntity<?> addTask(@RequestBody TaskDto taskDto) {
        ApiResponse response = taskService.addTask(taskDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/editTask/{id}")
    public HttpEntity<?> editTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        ApiResponse response = taskService.editTask(id,taskDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/changeYourTaskStatus/{statusId}")
    public HttpEntity<?> changeYourTaskStatus(@PathVariable Long statusId, @RequestBody StatusDto statusDto) {
        ApiResponse response = taskService.changeYourTaskStatus(statusId,statusDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/attachAFileToYourTask")
    public HttpEntity<?> attachAFileToYourTask(@PathVariable Long statusId, @RequestBody TaskDto taskDto) {
        ApiResponse response = taskService.attachAFileToYourTask(taskDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/deleteTheAttachedFile/{fileId}")
    public HttpEntity<?> deleteTheAttachedFile(@PathVariable Long fileId) {
        ApiResponse response = taskService.deleteTheAttachedFile(fileId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/addCommentToTask/{taskId}")
    public HttpEntity<?> addCommentToTask(@PathVariable Long taskId,@RequestBody CommentDto commentDto) {
        ApiResponse response = taskService.addCommentToTask(taskId,commentDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/addTagToTask/{taskId}")
    public HttpEntity<?> addTagToTask(@PathVariable Long taskId,@RequestBody TagDto tagDto) {
        ApiResponse response = taskService.addTagToTask(taskId,tagDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/changeTag/{tagId}")
    public HttpEntity<?> changeTag(@PathVariable Long tagId) {
        ApiResponse response = taskService.changeTag(tagId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/deleteTag/{tagId}")
    public HttpEntity<?> deleteTag(@PathVariable Long tagId) {
        ApiResponse response = taskService.deleteTag(tagId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/assignAUserToATask")
    public HttpEntity<?> assignAUserToATask(@RequestParam UUID userId,@RequestParam Long taskId) {
        ApiResponse response = taskService.assignAUserToATask(userId,taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @PostMapping("/removeAUserToATask")
    public HttpEntity<?> removeAUserToATask(@RequestParam UUID userId,@RequestParam Long taskId) {
        ApiResponse response = taskService.removeAUserToATask(userId,taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
