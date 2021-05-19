package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckListItem  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private CheckList checkList;

    private boolean resolved;

    @ManyToOne
    private User assignedUser;

    public CheckListItem(String name, CheckList checkList, boolean resolved, User assignedUser) {
        this.name = name;
        this.checkList = checkList;
        this.resolved = resolved;
        this.assignedUser = assignedUser;
    }
}
