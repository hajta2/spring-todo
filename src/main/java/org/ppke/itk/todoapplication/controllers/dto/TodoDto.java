package org.ppke.itk.todoapplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.ppke.itk.todoapplication.domain.Todo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private Long id;

    private String title;

    private String description;

    private Date startDate;

    private boolean done;

    // private Category category;

    public static Todo toEntity(TodoDto todoDto) {
        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.isDone());
        todo.setStartDate(todoDto.getStartDate());
        // todo.setCategory(todoDto.getCategory());

        return todo;
    }

    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .done(todo.isDone())
                .build();
    }
}
