package com.example.demo.repository;

import com.example.demo.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewRepository extends JpaRepository<View, Long> {

    @Query(value = "select * from view join view_space where space_id = ?1", nativeQuery = true)
    List<View> getAllBySpace(Long spaceId);

}
