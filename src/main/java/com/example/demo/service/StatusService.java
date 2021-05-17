package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;

public interface StatusService {
    public ApiResponse addStatus(StatusDto statusDto);
    public ApiResponse editStatus(StatusDto statusDto);

}
