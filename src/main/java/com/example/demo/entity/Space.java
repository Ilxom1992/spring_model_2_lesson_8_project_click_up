package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsLongEntity {
    private String name;

    private String color;

    private String initialLetter;

    @OneToOne
    private Attachment avatar;

    private boolean accessType;

    @ManyToOne
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
    @ManyToOne
    private Icon icon;

    @PrePersist
    @PreUpdate
    public void setInitials() {
        this.initialLetter= name.substring(0, 1);
    }

    public Space(String name, String color, String initialLetter, Attachment avatar, boolean accessType, Workspace workspace, User owner) {
        this.name = name;
        this.color = color;
        this.initialLetter = initialLetter;
        this.avatar = avatar;
        this.accessType = accessType;
        this.workspace = workspace;
        this.owner = owner;
    }
}
