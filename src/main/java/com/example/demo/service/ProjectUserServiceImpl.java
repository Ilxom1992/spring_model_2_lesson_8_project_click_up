package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectUser;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ProjectUserRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    final UserRepository userRepository;
    final TaskRepository taskRepository;
    final ProjectRepository projectRepository;
    final ProjectUserRepository projectUserRepository;

    public ProjectUserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository, ProjectUserRepository projectUserRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectUserRepository = projectUserRepository;
    }


    @Override
    public ApiResponse addMember(UUID memberId, Long projectId, Long taskId) {
        final Optional<User> optionalUser = userRepository.findById(memberId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("user not found", false);
        }

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }

        final Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent())
            return new ApiResponse("Project not found", false);

        ProjectUser projectUser = new ProjectUser(
                optionalProject.get(), optionalUser.get(), optionalTask.get()
        );
        projectUserRepository.save(projectUser);
        return new ApiResponse("User added to Project", true);
    }

    @Override
    public ApiResponse removeMember(UUID memberId, Long projectId, Long taskId) {

        final Optional<User> optionalUser = userRepository.findById(memberId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("user not found", false);
        }
        final User user = optionalUser.get();

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }
        final Task task = optionalTask.get();

        final Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent())
            return new ApiResponse("Project not found", false);

        final Project project = optionalProject.get();

        final boolean existsByProjectAndMemberAndTask = projectUserRepository.existsByProjectAndMemberAndTask(project, user, task);

        if (existsByProjectAndMemberAndTask) {
            projectUserRepository.deleteByProjectAndMemberAndTask(project, user, task);
        }
        return new ApiResponse("Removed", true);
    }


}
