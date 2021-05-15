package com.example.demo.repository;

import com.example.demo.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
   Optional<Workspace> findByIdAndOwnerId(Long id, UUID owner_id);
    @Query(value = "select * from workspace join users u on u.id = workspace.owner_id where u.id=?1",nativeQuery = true)
    List<Workspace> getWorkspaceByUserId(UUID userId);
}
