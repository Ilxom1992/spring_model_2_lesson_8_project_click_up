package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbstractEntity {
    private String name;

    private String color;

    private String initialLetter;

    @ManyToOne
    private Icon icon;

    @OneToOne
    private Attachment avatar;

    @Column(nullable = false)
    private String accessType;

    @ManyToOne
    private Workspace workspace;

    @OneToOne
    private User owner;



}
