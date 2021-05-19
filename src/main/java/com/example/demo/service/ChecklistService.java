package com.example.demo.service;



import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ChecklistDTO;
import com.example.demo.payload.ChecklistItemDTO;

import java.util.UUID;

public interface ChecklistService {

    ApiResponse add(ChecklistDTO checklistDTO);

    ApiResponse delete(Long id);

    ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO);

    ApiResponse removeChecklistItem(Long checklistItemId);

    ApiResponse assignUserToChecklistItem(Long checklistItemId, UUID userId);



}
