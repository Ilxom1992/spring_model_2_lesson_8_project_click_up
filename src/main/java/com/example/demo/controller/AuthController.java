package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    final AuthService authService;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;


    public AuthController(AuthService authService, AuthenticationManager authenticationManager,@Lazy JwtProvider jwtProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
//
//    @PostMapping("/register")
//    public HttpEntity<?>registerUser(@Valid @RequestBody RegisterDto registerDto){
//        ApiResponse register = authService.register(registerDto);
//        return ResponseEntity.status(register.isSuccess() ? 200:409).body(register);
//    }
//
//    @PostMapping("/login")
//    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//        User user= (User)authenticate.getPrincipal();
//        String token = jwtProvider.generateToken(user.getUsername(), user.getPosition());
//        return ResponseEntity.ok(token);
//    }
}
