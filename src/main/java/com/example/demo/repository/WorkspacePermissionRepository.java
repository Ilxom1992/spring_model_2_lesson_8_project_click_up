package com.example.demo.repository;



import com.example.demo.entity.WorkspacePermission;
import com.example.demo.entity.WorkspaceRole;
import com.example.demo.entity.enums.WorkspacePermissionName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {



   // boolean existsByWorkspaceRoleAndPermissionName(WorkspaceRole workspaceRole, String permission_name);
 //  void deleteByWorkspaceRoleAndPermissionName(WorkspaceRole workspaceRole, String permission_name);
}
