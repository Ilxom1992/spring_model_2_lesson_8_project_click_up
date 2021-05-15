package com.example.demo.entity;

import com.example.demo.entity.enums.TaskPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private User member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    public ProjectUser(Project project, User member, Task task) {
        this.project = project;
        this.member = member;
        this.task = task;
    }
}
