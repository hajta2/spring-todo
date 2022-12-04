package org.ppke.itk.todoapplication.repository;

import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
    List<ToDoItem> findAll();
}

