package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<com.example.demo.entity.Attachment, UUID> {
}
