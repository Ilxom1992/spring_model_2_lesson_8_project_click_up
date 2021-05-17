package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.WorkspaceRoleName;
import com.example.demo.payload.*;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;
    //ish xona qo'shhish
    @PostMapping("addWorkspace")
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * NAME, COLOR, AVATAR O'ZGARAISHI MUMKIN
     *
     * @param id
     * @param workspaceDTO
     * @return
     */

    //Workspace edit qilish
    @PutMapping("/editWorkspace/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO,
                                       @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.editWorkspace(id,workspaceDTO,user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * @param id
     * @param ownerId
     * @return
     */
    //ownerini o'zgartish
    @PutMapping("/changeOwner/{id}")
    public HttpEntity<?> changeOwnerWorkspace(@PathVariable Long id,
                                              @RequestParam UUID ownerId) {
        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, ownerId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * ISHXONANI O'CHIRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteWorkspace/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
//
    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id,
                                                    @RequestBody MemberDTO memberDTO) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/joinToWorkspace")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long id,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

  //  member va mehmonlarini ko'rish
  @GetMapping("/seeMembersAndGuests/{workSpaceId}")
  public HttpEntity<?> seeMembersAndGuests(@PathVariable Long workSpaceId) {
      ApiResponse apiResponse = workspaceService.seeMembersAndGuests(workSpaceId);
      return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
  }
  //  Workspacelari ro'yxatini olish
  @GetMapping("/getAListOfWorkspaces")
  public HttpEntity<?> getAListOfWorkspaces(@CurrentUser User user) {
      ApiResponse apiResponse = workspaceService.getAListOfWorkspaces(user);
      return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
  }
  //  Workspace ga role qo'shish
  @PostMapping("/addARoleToWorkspace/{workSpaceId}")
  public HttpEntity<?> addARoleToWorkspace(@PathVariable Long workSpaceId, @RequestBody RoleDto roleDto) {
      ApiResponse apiResponse = workspaceService.addARoleToWorkspace( roleDto,workSpaceId);
      return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
  }
  //Workspace rolelarini permission berish yoki olib tashlash
  @PostMapping("/permissionOrRemovalOfWorkspaceRoles")
  public HttpEntity<?> SetPermissionWorkspaceRoles(@RequestBody  WorkspaceRoleDTO workspacePermissionDTO) {
      ApiResponse apiResponse = workspaceService.permissionOrRemovalOfWorkspaceRoles(workspacePermissionDTO);
      return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
  }
  @PostMapping("/addRole/{workSpaceId}")
  public ApiResponse addRole(@PathVariable Long workSpaceId, @RequestBody WorkspaceRoleDTO workspaceRoleDTO){
     return    workspaceService.addRole(workSpaceId,workspaceRoleDTO);

  }
    @PostMapping("/permissionOrRemoval")
    public HttpEntity<?> permissionOrRemovalRoles(@RequestBody  WorkspacePermissionDTO workspacePermissionDTO) {
        ApiResponse apiResponse = workspaceService.deleteRolePermission(workspacePermissionDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
