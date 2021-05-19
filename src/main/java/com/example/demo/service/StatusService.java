package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.StatusDto;

import javax.persistence.Id;

public interface StatusService {
    public ApiResponse addStatus(StatusDto statusDto, Long taskId);

    //Edit qilish task servisda yozilgan

}
