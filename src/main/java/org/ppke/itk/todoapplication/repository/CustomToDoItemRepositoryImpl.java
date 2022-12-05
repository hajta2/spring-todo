package org.ppke.itk.todoapplication.repository;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.domain.ToDoPriority;
import org.ppke.itk.todoapplication.domain.ToDoStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
public class CustomToDoItemRepositoryImpl implements CustomToDoItemRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public ToDoItem saveToDoItem(String title, String details, ToDoPriority priority, ToDoStatus status) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle(title);
        toDoItem.setDetails(details);
        toDoItem.setPriority(priority);
        toDoItem.setStatus(status);
        toDoItem.setCreationDate(new Date());
        entityManager.persist(toDoItem);
        return toDoItem;
    }
}
