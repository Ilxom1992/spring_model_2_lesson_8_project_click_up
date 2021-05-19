package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ChecklistDTO;
import com.example.demo.payload.ChecklistItemDTO;
import com.example.demo.service.ChecklistService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/checklist")
public class ChecklistController {

  final   ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ChecklistDTO checklistDTO) {
        final ApiResponse add = checklistService.add(checklistDTO);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        final ApiResponse add = checklistService.delete(id);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @PostMapping("/addChecklistItem")
    public HttpEntity<?> addChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO) {
        final ApiResponse add = checklistService.addChecklistItem(checklistItemDTO);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @DeleteMapping("/removeChecklistItem/{id}")
    public HttpEntity<?> removeChecklistItem(@PathVariable Long id) {
        final ApiResponse add = checklistService.removeChecklistItem(id);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @PostMapping("/assignUserToChecklistItem")
    public HttpEntity<?> assignUserToChecklistItem(@RequestParam Long checklistItemId, @RequestParam UUID userId){
        final ApiResponse add = checklistService.assignUserToChecklistItem(checklistItemId, userId);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
}
