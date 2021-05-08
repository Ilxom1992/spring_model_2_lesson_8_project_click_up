package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbstractEntity;
import com.example.demo.entity.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspacePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WorkspaceRole workspaceRole;

    @Enumerated(EnumType.STRING)
    private Permission permission;
}
