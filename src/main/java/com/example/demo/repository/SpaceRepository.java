package com.example.demo.repository;


import com.example.demo.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
