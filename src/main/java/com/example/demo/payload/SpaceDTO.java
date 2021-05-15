package com.example.demo.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SpaceDTO {

    private String name;
    private String color;
    private String initials;
    private UUID avatarId;
    private boolean accessType;
    private Long workspaceId;
    private UUID ownerId;
}
