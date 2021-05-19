package com.example.demo.payload;

import com.example.demo.entity.Task;
import lombok.Data;

import javax.persistence.ManyToOne;
@Data
public class CommentDto {

    private String name;


    private Long taskId;
}
