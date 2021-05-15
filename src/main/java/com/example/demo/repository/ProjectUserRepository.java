package com.example.demo.repository;


import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectUser;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    boolean existsByProjectAndMemberAndTask(Project project, User member, Task task);

    boolean deleteByProjectAndMemberAndTask(Project project, User member, Task task);

}
