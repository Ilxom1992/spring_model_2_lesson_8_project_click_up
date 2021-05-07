package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Fullname bolmasligi kerak")
    private String fullName;

    @NotNull(message = "username bosh bolmasligi kerak")
    private String username;

    @NotNull(message = "password bosh bolmasligi kerak")
    private String password;

    @NotNull(message = "Lavozim bosh bolmasligi kerak")
    private Integer positionId;
}
