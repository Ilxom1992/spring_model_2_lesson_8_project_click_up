package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Override
    public ApiResponse addStatus(StatusDto statusDto, Long taskId) {
        return null;
    }
}
