package org.ppke.itk.todoapplication.repositories;

import java.util.List;

import org.ppke.itk.todoapplication.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByAccountId(Long accountId);

    @Query("select t.category from Todo t where t.id = :todoId")
    Category findCategoryByTodoId(Long todoId);

}
