package org.ppke.itk.todoapplication.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.domain.ToDoPriority;
import org.ppke.itk.todoapplication.domain.ToDoStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItemDto {
    private Integer Id;
    private String title;
    private String details;
    private ToDoStatus status;
    private ToDoPriority priority;

    public static ToDoItemDto fromItem(ToDoItem toDoItem) {
        return new ToDoItemDto(toDoItem.getId(), toDoItem.getTitle(), toDoItem.getDetails(), toDoItem.getStatus(),
                toDoItem.getPriority());
    }
}
