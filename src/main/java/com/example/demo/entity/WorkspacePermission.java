package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsUUIDEntity;

import com.example.demo.entity.enums.WorkspacePermissionName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspacePermission extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole;//O'RINBOSAR

    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permission;//add member,remove member
}

