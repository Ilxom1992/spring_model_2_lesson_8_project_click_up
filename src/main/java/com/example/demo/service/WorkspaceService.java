package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.entity.Workspace;
import com.example.demo.entity.enums.WorkspaceRoleName;
import com.example.demo.payload.*;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO,User user);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    ApiResponse seeMembersAndGuests(Long workSpaceId);//member va mehmonlarini ko'rish

    ApiResponse getAListOfWorkspaces(User user);//Workspacelari ro'yxatini olish

    ApiResponse addARoleToWorkspace(RoleDto roleDto, Long workSpaceId);// Workspace ga role qo'shish

    ApiResponse permissionOrRemovalOfWorkspaceRoles(WorkspaceRoleDTO workspacePermissionDTO);// Workspace rolelarini permisison berish yoki olib tashlash

    ApiResponse deleteRolePermission(WorkspacePermissionDTO workspacePermissionDTO);
}
