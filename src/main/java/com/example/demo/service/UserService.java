package com.example.demo.service;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse addUser(UserDto userDto, HttpServletRequest httpServletRequest) {
        return null;
    }
}
