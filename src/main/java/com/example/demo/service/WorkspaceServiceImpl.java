package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AddType;
import com.example.demo.entity.enums.WorkspacePermissionName;
import com.example.demo.entity.enums.WorkspaceRoleName;
import com.example.demo.payload.*;
import com.example.demo.repository.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        //WORKSPACE OCHDIK
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName()))
            return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);

        //WORKSPACE ROLE OCHDIK
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWNERGA HUQUQLARNI BERYAPAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames)
        {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));

        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(Long id,WorkspaceDTO workspaceDTO,User user) {

        Optional<Workspace> optionalWorkspace = workspaceRepository.findByIdAndOwnerId(id, user.getId());
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Sizda bunday nomli ishxona mavjud emas", false);
        Workspace workspace = optionalWorkspace.get();
        workspace.setName(workspaceDTO.getName());
        workspace.setColor(workspaceDTO.getColor());
        workspace.setAvatar(workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment")));
        workspaceRepository.save(workspace);
        return new ApiResponse("O'zgartirildi", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (!optionalWorkspace.isPresent())
            return new ApiResponse("Sizda bunday nomli ishxona mavjud emas", false);
        Workspace workspace = optionalWorkspace.get();
       workspace.setOwner(userRepository.findById(ownerId).get());
        workspaceRepository.save(workspace);
        return new ApiResponse("WorkSpace egasi o'zgartirildi", true);
    }
    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);

        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Muvaffaqiyatli", true);
    }
    //TODO EMAILGA INVITE XABAR YUBORISH
    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDataJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    //VAZIFALAR

  // member va mehmonlarini ko'rish
    @Override
    public ApiResponse seeMembersAndGuests(Long workSpaceId) {
        List<User> optionalList = userRepository.getAllByMember(workSpaceId);
        return new ApiResponse("Topildi",true,optionalList);
    }

    @Override
    public ApiResponse getAListOfWorkspaces(User user) {
        List<Workspace> workspaceByUserId = workspaceRepository.getWorkspaceByUserId(user.getId());
        return new ApiResponse("All Work Space List",true,workspaceByUserId);
    }

    @Override
    public ApiResponse addARoleToWorkspace(RoleDto roleDto, Long workSpaceId) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workSpaceId);

        Workspace workspace = optionalWorkspace.get();
        for (String role:roleDto.getRoleList()) {
            workspaceRoleRepository.save(new WorkspaceRole(workspace, role,null));
        }
       return new ApiResponse("Role Berildi",true);
    }

    @Override
    public ApiResponse permissionOrRemovalOfWorkspaceRoles(WorkspacePermissionDTO workspacePermissionDTO) {

        final Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceRoleId());

        final WorkspaceRole workspaceRole = optionalWorkspaceRole.get();

        String permissionName=workspacePermissionDTO.getPermissionName();
//        final boolean existsByWorkspaceRoleAndPermissionName =
//                workspacePermissionRepository.existsByWorkspaceRoleAndPermissionName(
//                        workspaceRole,permissionName
//                );
        WorkspacePermissionName workspacePermissionName = null;
        final WorkspacePermissionName[] values = WorkspacePermissionName.values();
        for (WorkspacePermissionName value : values) {
            if (value.getName().equals(workspacePermissionDTO.getPermissionName())) {
                workspacePermissionName = value;
            }
        }

//        if (existsByWorkspaceRoleAndPermissionName) {
//            return new ApiResponse("Role '" + workspaceRole.getName() +
//                    "' already has permission '" + workspacePermissionDTO.getPermissionName() + "'", false);
//        }

        WorkspacePermission workspacePermission = new WorkspacePermission(
                workspaceRole, workspacePermissionName
        );
        workspacePermissionRepository.save(workspacePermission);

        return new ApiResponse("Permission set!", true);
    }
    @Override
    public ApiResponse deleteRolePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        final Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceRoleId());

        final WorkspaceRole workspaceRole = optionalWorkspaceRole.get();


String permissionName=workspacePermissionDTO.getPermissionName();
//        final boolean existsByWorkspaceRoleAndPermissionName =
//                workspacePermissionRepository.existsByWorkspaceRoleAndPermissionName(
//                        workspaceRole, permissionName
//                );
        WorkspacePermissionName workspacePermissionName = null;
        final WorkspacePermissionName[] permissions = WorkspacePermissionName.values();
        for (WorkspacePermissionName permission : permissions) {
            if (permission.getName().equals(workspacePermissionDTO.getPermissionName())) {
                workspacePermissionName = permission;
            }
        }


//        if (existsByWorkspaceRoleAndPermissionName) {
//            workspacePermissionRepository.deleteByWorkspaceRoleAndPermissionName(workspaceRole, permissionName);
//            return new ApiResponse("Deleted", true);
//        }


        assert workspacePermissionName != null;
        return new ApiResponse("Workspace role with ' not exists!", false);

    }
}
