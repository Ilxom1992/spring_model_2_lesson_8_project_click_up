package com.example.demo.repository;

import com.example.demo.entity.Tamplate.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position> findByName(String name);

    boolean existsByName(String name);
}
