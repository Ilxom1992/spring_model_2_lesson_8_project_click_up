package com.example.demo.payload;

import com.example.demo.entity.Category;
import com.example.demo.entity.Priority;
import com.example.demo.entity.Status;
import com.example.demo.entity.Task;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;
@Data
public class TaskDto {


    private String name;


    private String description;


    private Long statusId;


    private Long categoryId;


    private Long priorityId;


    private Long parentTaskId;

    private Date startedDate;

    private boolean startTimeHas;

    private Date dueDate;

    private boolean dueTimeHas;

    private Long estimateTime;

    private Timestamp activeDate;
}
