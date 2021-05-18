package com.example.demo.controller;

import com.example.demo.entity.Status;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SpaceDTO;
import com.example.demo.payload.StatusDto;
import com.example.demo.payload.TaskDto;
import com.example.demo.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
