package com.example.demo.payload;

import com.example.demo.entity.enums.WorkspacePermissionName;
import com.example.demo.entity.enums.WorkspaceRoleName;
import lombok.Data;

import java.util.UUID;

@Data
public class WorkspacePermissionDTO {

    private UUID workspaceRoleId;
    private String name;
    private WorkspaceRoleName extendsRole;
    private WorkspacePermissionName permissionName;
}

