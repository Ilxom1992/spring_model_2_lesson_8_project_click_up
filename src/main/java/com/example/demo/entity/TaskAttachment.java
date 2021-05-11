package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskAttachment extends AbsLongEntity {
    @ManyToOne
    private Task task;

    @ManyToOne
    private Attachment attachment;

    private boolean pinCoverImage = false;
}
