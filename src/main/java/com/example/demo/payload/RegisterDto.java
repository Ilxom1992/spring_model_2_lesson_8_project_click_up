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

@NotNull(message = "password bosh bolmasligi kerak")
    private String password;

@NotNull(message = "Email bo'sh bo'lmasligi kerak")
    private String email;

}
