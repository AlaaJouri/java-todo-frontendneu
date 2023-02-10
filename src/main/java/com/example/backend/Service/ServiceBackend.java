package com.example.backend.Service;
import com.example.backend.Pepo.RepoBackend;
import com.example.backend.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ServiceBackend {

        private final RepoBackend repoBackend;

    public ServiceBackend(RepoBackend repoBackend) {
        this.repoBackend = repoBackend;
    }

    public List<Todo> getTodos()
    {
        return repoBackend.getTodos();
    }

    public Todo getTodoById (String id) {
        return repoBackend.getTodoById(id);
    }
    public Todo addTodo(Todo todo) {
        String Id= UUID.randomUUID().toString();
        Todo ogT=todo.Td(Id);
        repoBackend.addTodo(todo);
        return todo ;
    }

    public Todo AktuTodo(String id,Todo todoo)
    {

       return repoBackend.aktuTodo(id,todoo);
    }
    public void deleteTodo(String id) {
         repoBackend.deleteTodo(id);
    }





}
