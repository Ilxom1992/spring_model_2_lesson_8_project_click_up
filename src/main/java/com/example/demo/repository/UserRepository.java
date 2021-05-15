package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

@Query(value = "select * from users join workspace_user wu on users.id = wu.user_id where workspace_id=?1",nativeQuery = true)
    List<User> getAllByMember(long workSpaceId);
}
