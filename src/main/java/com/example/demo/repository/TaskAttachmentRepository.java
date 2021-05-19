package com.example.demo.repository;

import com.example.demo.entity.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

    Optional<TaskAttachment> findByTaskIdAndAttachmentId(Long task_id, UUID attachment_id);

}
