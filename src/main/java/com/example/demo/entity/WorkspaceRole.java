package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsUUIDEntity;
import com.example.demo.entity.enums.WorkspaceRoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"workspace_id,name"}))
public class WorkspaceRole extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String name;


@Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}
