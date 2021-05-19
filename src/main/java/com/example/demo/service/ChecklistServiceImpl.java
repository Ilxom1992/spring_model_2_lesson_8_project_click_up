package com.example.demo.service;

import com.example.demo.entity.CheckList;
import com.example.demo.entity.CheckListItem;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ChecklistDTO;
import com.example.demo.payload.ChecklistItemDTO;
import com.example.demo.repository.ChecklistItemRepository;
import com.example.demo.repository.ChecklistRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    final TaskRepository taskRepository;
    final ChecklistRepository checklistRepository;
    final ChecklistItemRepository checklistItemRepository;
    final UserRepository userRepository;

    public ChecklistServiceImpl(TaskRepository taskRepository, ChecklistRepository checklistRepository, ChecklistItemRepository checklistItemRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.checklistRepository = checklistRepository;
        this.checklistItemRepository = checklistItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse add(ChecklistDTO checklistDTO) {

        final Optional<Task> optionalTask = taskRepository.findById(checklistDTO.getTaskId());
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }
        CheckList checklist = new CheckList();
                checklistDTO.getName();

        checklistRepository.save(checklist);

        return new ApiResponse("Checklist added!", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final Optional<CheckList> optionalChecklist = checklistRepository.findById(id);
        if (!optionalChecklist.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        checklistRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    @Override
    public ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO) {

        final Optional<CheckList> optionalChecklist = checklistRepository.findById(checklistItemDTO.getChecklistId());
        if (!optionalChecklist.isPresent()) {
            return new ApiResponse("Checklist not found", false);
        }

        final Optional<User> optionalUser = userRepository.findById(checklistItemDTO.getAssignedUser());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Assigned user not found", false);
        }

        CheckListItem checklistItem = new CheckListItem(
                checklistItemDTO.getName(),
                optionalChecklist.get(),
                checklistItemDTO.isResolved(),
                optionalUser.get()
        );
        checklistItemRepository.save(checklistItem);
        return new ApiResponse("Checklist Item added", true);
    }

    @Override
    public ApiResponse removeChecklistItem(Long checklistItemId) {

        final Optional<CheckListItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (!optionalChecklistItem.isPresent()) {
            checklistItemRepository.deleteById(checklistItemId);
            return new ApiResponse("Removed", true);
        }
        return new ApiResponse("Not found", false);
    }

    @Override
    public ApiResponse assignUserToChecklistItem(Long checklistItemId, UUID userId) {

        final Optional<CheckListItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (!optionalChecklistItem.isPresent()) {
            return new ApiResponse("Checklist Item not found", false);
        }
        final CheckListItem checklistItem = optionalChecklistItem.get();

        final Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }

        checklistItem.setAssignedUser(optionalUser.get());
        return new ApiResponse("user assigned", true);
    }
}
