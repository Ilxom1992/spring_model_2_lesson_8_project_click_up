package com.example.demo.payload;

import com.example.demo.entity.Workspace;
import lombok.Data;


@Data
public class TagDto {

    private String name;


    private String color;


    private Long workspaceId;
}
