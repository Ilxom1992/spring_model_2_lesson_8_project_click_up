package com.example.demo.repository;



import com.example.demo.entity.WorkspacePermission;
import com.example.demo.entity.WorkspaceRole;
import com.example.demo.entity.enums.WorkspacePermissionName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {

    Optional<WorkspacePermission> findByWorkspaceRoleId(UUID workspaceRole_id);
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);

    List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(String workspaceRole_name, Long workspaceRole_workspace_id);

}
