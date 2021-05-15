package com.example.demo.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspacePermissionDTO {

    private UUID workspaceRoleId;
    private String permissionName;
}

