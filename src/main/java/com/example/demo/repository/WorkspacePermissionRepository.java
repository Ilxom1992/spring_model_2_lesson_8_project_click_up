package com.example.demo.repository;



import com.example.demo.entity.WorkspacePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}
