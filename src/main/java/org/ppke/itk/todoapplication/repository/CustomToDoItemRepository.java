package org.ppke.itk.todoapplication.repository;

import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.domain.ToDoPriority;
import org.ppke.itk.todoapplication.domain.ToDoStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomToDoItemRepository {
    ToDoItem saveToDoItem(String title, String details, ToDoPriority priority, ToDoStatus status);
}
