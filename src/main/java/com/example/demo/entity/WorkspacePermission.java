package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsUUIDEntity;
import com.example.demo.entity.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspacePermission extends AbsUUIDEntity {


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private WorkspaceRole workspaceRole;//o'rinbosar

    @Enumerated(EnumType.STRING)
    private Permission permission;//add member ,remove member
}
