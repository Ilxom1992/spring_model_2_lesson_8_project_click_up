package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceUser extends AbstractEntity {

    @ManyToOne
    private Workspace workspace;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkspaceRole workspaceRole;

    private Date dateInvited;

    private Date dataInvited;
}
