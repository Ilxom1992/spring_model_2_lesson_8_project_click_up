package com.example.demo.service;


import com.example.demo.payload.ApiResponse;

import java.util.UUID;

public interface ProjectUserService {

    ApiResponse addMember(UUID memberId, Long projectId, Long taskId);

    ApiResponse removeMember(UUID memberId, Long projectId, Long taskId);
}
