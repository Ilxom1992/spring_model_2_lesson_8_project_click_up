package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Workspace extends AbstractEntity {

    private String name;

    private String color;

    @ManyToOne
    private User owner;

    private String initialLetter;

    @OneToOne
    private Attachment avatar;
}
