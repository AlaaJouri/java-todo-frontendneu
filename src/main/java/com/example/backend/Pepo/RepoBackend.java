package com.example.backend.Pepo;
import com.example.backend.model.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Todo;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RepoBackend {
    private final List<Todo> todos ;
    public List<Todo> getTodos()
    {
        return todos;
    }

    public Todo getTodoById (String id) {
        return todos.stream()
                .filter(todo ->todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Todo addTodo(Todo todo) {
        todos.add(todo);
        return todo ;
    }

public Todo aktuTodo(String id,Todo todo)
{
    todos.stream()
            .filter(todo1 -> todo1.getId().equals(id))
            .findFirst()
            .ifPresent(todo1 -> {
                todo1.setDescription(todo.getDescription());
                todo1.setStatus(todo.getStatus());
            });
    return todo;
}
    public void deleteTodo(String id) {
         todos.removeIf(todo -> todo.getId().equals(id));
    }
    }





