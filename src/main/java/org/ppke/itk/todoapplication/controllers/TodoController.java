package org.ppke.itk.todoapplication.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.http.HttpHeaders;
import org.ppke.itk.todoapplication.controllers.dto.CategoryDto;
import org.ppke.itk.todoapplication.controllers.dto.TodoDto;
import org.ppke.itk.todoapplication.domain.Todo;
import org.ppke.itk.todoapplication.repositories.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public List<TodoDto> getTodos(@RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "desc") String sort) {
        if (!sort.equalsIgnoreCase("desc") && !sort.equalsIgnoreCase("asc")) {
            throw new IllegalArgumentException("Invalid sorting param!!!");
        }
        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "startDate")
                : Sort.by(Sort.Direction.DESC, "startDate");

        Page<Todo> todos = todoRepository.findAll(PageRequest.of(0, limit, sortParam));
        return todos.stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping(produces = "text/plain")
    public ResponseEntity<Resource> getTodosAsTxt() throws IOException {

        byte[] binaryData = FileCopyUtils.copyToByteArray((new ClassPathResource("static/todos.txt")).getInputStream());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=teams.txt");

        ByteArrayResource resource = new ByteArrayResource(binaryData);

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(resource.contentLength())
                .contentType(org.springframework.http.MediaType.parseMediaType("text/plain"))
                .body(resource);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    @PutMapping("/{id}")
    public TodoDto updateTodoById(Long id, @RequestBody TodoDto todoDto) {
        final Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.isDone());
        todo.setStartDate(todoDto.getStartDate());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

        return TodoDto.fromEntity(todoRepository.save(todo));
    }
}
