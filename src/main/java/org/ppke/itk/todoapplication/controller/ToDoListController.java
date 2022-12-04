package org.ppke.itk.todoapplication.controller;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.repository.ToDoItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ToDoItem")
public class ToDoListController {
    private final ToDoItemRepository toDoItemRepository;

    @GetMapping
    public List<ToDoItem> getAllToDoItems() {
        return toDoItemRepository.findAll();
    }

    @PostMapping
    public void createToDoItem(ToDoItem toDoItem) {
        toDoItemRepository.save(toDoItem);
    }
}
