package com.example.demo.payload;

import com.example.demo.entity.enums.AddType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.sql.Timestamp;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    private UUID id;

    private String fullName;

    private  String email;

    private String roleName;

    private Timestamp lastActive;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}
