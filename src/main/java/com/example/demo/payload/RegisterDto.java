package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
@NotNull(message = "Fullname bolmasligi kerak")
    private String fullName;

@NotNull(message = "username bosh bolmasligi kerak")
    private String username;

@NotNull(message = "password bosh bolmasligi kerak")
    private String password;

@NotNull(message = "password takrori bosh bolmasligi kerak")
    private String prePassword;
@NotNull(message = "Email bo'sh bo'lmasligi kerak")
    private String email;

}
