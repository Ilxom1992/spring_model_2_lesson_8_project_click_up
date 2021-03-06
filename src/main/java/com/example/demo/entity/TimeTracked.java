package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTracked extends AbsLongEntity {
    @ManyToOne
    private Task task;

    private Timestamp startAt;

    private Timestamp stoppedAt;
}
