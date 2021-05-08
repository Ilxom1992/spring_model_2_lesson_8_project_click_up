package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Space space;

    @Column(nullable = false)
    private boolean accessType;

    private boolean archived;

    @Column(nullable = false)
    private String color;
}
