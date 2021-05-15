package com.example.demo.controller;

import com.example.demo.entity.Space;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SpaceDTO;
import com.example.demo.service.SpaceService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/space")
public class SpaceController {

   final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/add")
    public HttpEntity<?> addSpace(@RequestBody SpaceDTO spaceDTO) {
        ApiResponse response = spaceService.add(spaceDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editSpace(@RequestBody SpaceDTO spaceDTO, @PathVariable Long id) {
        ApiResponse response = spaceService.edit(spaceDTO, id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSpace(@PathVariable Long id) {
        ApiResponse response = spaceService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping
    public HttpEntity<?> getSpace() {
        List<Space> spaceList = spaceService.get();
        return ResponseEntity.status(spaceList.isEmpty() ? 409 : 201).body(spaceList);
    }


}
