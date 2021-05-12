package com.example.demo.payload;

import com.example.demo.entity.enums.AddType;
import lombok.Data;


import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}
