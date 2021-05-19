package com.example.demo.repository;

import com.example.demo.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskUserRepository extends JpaRepository<TaskUser,Long> {

}
