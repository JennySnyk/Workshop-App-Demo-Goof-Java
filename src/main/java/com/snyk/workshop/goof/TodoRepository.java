package com.snyk.workshop.goof;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TodoRepository {
    private List<Todo> todos = new ArrayList<>();

    public TodoRepository() {
        // Pre-populate with some data
        todos.add(new Todo("Set up the security demo"));
        todos.add(new Todo("Add a dozen SCA vulnerabilities"));
        todos.add(new Todo("Add a dozen SAST vulnerabilities"));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public void save(Todo todo) {
        todos.add(todo);
    }

    public void deleteById(long id) {
        todos = todos.stream()
                     .filter(todo -> todo.getId() != id)
                     .collect(Collectors.toList());
    }
}
