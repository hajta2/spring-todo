package org.ppke.itk.todoapplication.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.ppke.itk.todoapplication.controllers.dto.CategoryDto;
import org.ppke.itk.todoapplication.controllers.dto.TodoDto;
import org.ppke.itk.todoapplication.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<TodoDto> getTodosByCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).get().getTodoList().stream().map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
