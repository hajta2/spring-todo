package org.ppke.itk.todoapplication.controller;

import lombok.RequiredArgsConstructor;
import org.ppke.itk.todoapplication.controller.dto.ToDoItemDto;
import org.ppke.itk.todoapplication.domain.ToDoItem;
import org.ppke.itk.todoapplication.domain.ToDoPriority;
import org.ppke.itk.todoapplication.domain.ToDoStatus;
import org.ppke.itk.todoapplication.repository.ToDoItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ToDoItem")
public class ToDoListController {
    private final ToDoItemRepository toDoItemRepository;

    @GetMapping
    public List<ToDoItem> getAllToDoItems(@RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "desc") String sort,
            @RequestParam(required = false, defaultValue = "none") String status,
            @RequestParam(required = false, defaultValue = "none") String priority) {
        if (!sort.equalsIgnoreCase("desc") && !sort.equalsIgnoreCase("asc")) {
            throw new IllegalArgumentException("Invalid sorting param!!!");
        }

        if (!status.equalsIgnoreCase("none") && !status.equalsIgnoreCase("done")
                && !status.equalsIgnoreCase("in_progress")) {
            throw new IllegalArgumentException("Invalid status param!!!");
        }

        if (!priority.equalsIgnoreCase("none") && !priority.equalsIgnoreCase("major")
                && !priority.equalsIgnoreCase("minor") && !priority.equalsIgnoreCase("critical")) {
            throw new IllegalArgumentException("Invalid priority param!!!");
        }

        var sortParam = sort.equalsIgnoreCase("desc") ? Sort.by(Sort.Direction.DESC, "creationDate")
                : Sort.by(Sort.Direction.ASC, "creationDate");

        List<ToDoItem> toDoItems = null;
        if (status.equalsIgnoreCase("none") && priority.equalsIgnoreCase("none")) {
            Page<ToDoItem> items = toDoItemRepository.findAll(
                    org.springframework.data.domain.PageRequest.of(0, limit, sortParam));
            toDoItems = items.toList();

        }
        if (!status.equalsIgnoreCase("none") && priority.equalsIgnoreCase("none")) {
            toDoItems = toDoItemRepository.findAllByStatus(ToDoStatus.valueOf(status.toUpperCase()));
        }

        if (status.equalsIgnoreCase("none") && !priority.equalsIgnoreCase("none")) {
            toDoItems = toDoItemRepository.findAllByPriority(ToDoPriority.valueOf(priority.toUpperCase()));
        }

        if (!status.equalsIgnoreCase("none") && !priority.equalsIgnoreCase("none")) {
            toDoItems = toDoItemRepository.findAllByStatusAndPriority(ToDoStatus.valueOf(status.toUpperCase()),
                    ToDoPriority.valueOf(priority.toUpperCase()));
        }

        return toDoItems;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ToDoItemDto getToDoItemById(@PathVariable Integer id) {
        return ToDoItemDto.fromItem(toDoItemRepository.findById(id).orElseThrow());
    }

    @PostMapping(consumes = "application/json")
    public ToDoItemDto createToDoItem(@RequestBody ToDoItemDto toDoItemDto) {
        ToDoItem item = toDoItemRepository.saveToDoItem(toDoItemDto.getTitle(), toDoItemDto.getDetails(),
                toDoItemDto.getPriority(), toDoItemDto.getStatus());
        return ToDoItemDto.fromItem(item);
    }

    @PutMapping("/{id}")
    public void updateToDoItem(@PathVariable Integer id, @RequestBody ToDoItemDto toDoItemDto) {
        ToDoItem item = toDoItemRepository.findById(id).orElseThrow();
        item.setTitle(toDoItemDto.getTitle());
        item.setDetails(toDoItemDto.getDetails());
        item.setPriority(toDoItemDto.getPriority());
        item.setStatus(toDoItemDto.getStatus());
        toDoItemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoItem(@PathVariable Integer id) {
        toDoItemRepository.deleteById(id);
    }
}
