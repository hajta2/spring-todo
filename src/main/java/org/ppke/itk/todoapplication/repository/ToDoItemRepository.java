package org.ppke.itk.todoapplication.repository;

import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.domain.ToDoPriority;
import org.ppke.itk.todoapplication.domain.ToDoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer>, CustomToDoItemRepository {
    List<ToDoItem> findAll();

    @Query("SELECT t FROM ToDoItem t WHERE t.status = ?1")
    List<ToDoItem> findAllByStatus(ToDoStatus status);

    @Query("SELECT t FROM ToDoItem t WHERE t.priority = ?1")
    List<ToDoItem> findAllByPriority(ToDoPriority priority);

    @Query("SELECT t FROM ToDoItem t WHERE t.status = ?1 AND t.priority = ?2")
    List<ToDoItem> findAllByStatusAndPriority(ToDoStatus valueOf, ToDoPriority valueOf1);
}
