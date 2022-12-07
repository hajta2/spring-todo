package org.ppke.itk.todoapplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.ppke.itk.todoapplication.domain.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    // private AccountDto account;

    @JsonIgnore
    private List<TodoDto> todoList;

    public static Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();

        // category.setAccount(AccountDto.toEntity(categoryDto.getAccount()));
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .todoList(
                        category.getTodoList() != null
                                ? category.getTodoList().stream().map(TodoDto::fromEntity).collect(Collectors.toList())
                                : null)
                .build();
    }
}
