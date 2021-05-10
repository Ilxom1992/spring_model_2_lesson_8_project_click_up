package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/registerUser")
    public HttpEntity<?> register(@RequestBody UserDto userDto, HttpServletRequest httpServletRequest) {
        final ApiResponse response = userService.addUser(userDto,httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}
