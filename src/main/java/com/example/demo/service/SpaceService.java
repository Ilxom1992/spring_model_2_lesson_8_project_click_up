package com.example.demo.service;


import com.example.demo.entity.Space;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SpaceDTO;

import java.util.List;

public interface SpaceService {
    ApiResponse add(SpaceDTO spaceDTO);


    ApiResponse edit(SpaceDTO spaceDTO, Long id);

    ApiResponse delete(Long id);

    List<Space> get();

}
