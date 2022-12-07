package org.ppke.itk.todoapplication.repositories;

import java.util.List;

import org.ppke.itk.todoapplication.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodoByCategoryId(Long categoryId);
}
