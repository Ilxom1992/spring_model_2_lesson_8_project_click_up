package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsLongEntity;
import com.example.demo.entity.enums.DependencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskDependency extends AbsLongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private TaskDependency dependencyTask;

    @Enumerated(EnumType.STRING)
    private DependencyType dependencyType;

}
