package com.example.backend.Controller;

import com.example.backend.Pepo.RepoBackend;
import com.example.backend.Service.ServiceBackend;
import com.example.backend.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ControllerBackend {

    private final ServiceBackend serviceBackend;

    @GetMapping("/todo")
    public List<Todo> listtodos() {
        return serviceBackend.getTodos();
    }

    @GetMapping("/todo/{id}")
    public Todo getProduct(@PathVariable String id) {
        return serviceBackend.getTodoById(id);
    }

    @PostMapping("/todo")
    public Todo addProduct(@RequestBody Todo todo) {
        return serviceBackend.addTodo(todo);
    }
    @PutMapping ("/todo/{id}")
    public Todo akturoduct(@PathVariable String id,@RequestBody  Todo todo) {
        return serviceBackend.AktuTodo(id,todo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteProduct(@PathVariable String id) {
         serviceBackend.deleteTodo(id);
    }

}
