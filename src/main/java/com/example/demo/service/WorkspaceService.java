package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.MemberDTO;
import com.example.demo.payload.WorkspaceDTO;

import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);
}
