package com.example.demo.repository;


import com.example.demo.entity.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistItemRepository extends JpaRepository<CheckListItem, Long> {
}
