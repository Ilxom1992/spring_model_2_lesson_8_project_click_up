package com.example.demo.payload;

import com.example.demo.entity.Category;
import com.example.demo.entity.Project;
import com.example.demo.entity.Space;
import com.example.demo.entity.enums.StatusType;

import javax.persistence.*;

public class StatusDto {


    private String name;


    private Long spaceId;


    private Long projectId;


    private Long categoryId;


    private String color;


    private StatusType type;
}
