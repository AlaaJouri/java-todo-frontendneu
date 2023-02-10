package com.example.backend.Service;

import com.example.backend.Pepo.RepoBackend;
import com.example.backend.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ServiceBackendTest {
    private ServiceBackend serviceBackend;
    private RepoBackend repoBackend;

    @BeforeEach
    public void setUp() {
        repoBackend = mock(RepoBackend.class);
        serviceBackend = new ServiceBackend(repoBackend);
    }

    @Test
    public void testGetTodos() {
        List<Todo> expectedTodos = new ArrayList<Todo>();
        expectedTodos.add(new Todo("1", "Todo 1","OPEN"));
        expectedTodos.add(new Todo("1", "Todo 1","OPEN"));
        when(repoBackend.getTodos()).thenReturn(expectedTodos);

        List<Todo> todos = serviceBackend.getTodos();

        assertEquals(expectedTodos, todos);
    }

    @Test
    public void testGetTodoById() {
        Todo expectedTodo = new Todo("1", "Todo 1","OPEN");
        when(repoBackend.getTodoById("1")).thenReturn(expectedTodo);

        Todo todo = serviceBackend.getTodoById("1");

        assertEquals(expectedTodo, todo);
    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo("1", "Todo 1","OPEN");
        serviceBackend.addTodo(todo);

        verify(repoBackend).addTodo(todo);
    }

    @Test
    public void testAktuTodo() {
        Todo expectedTodo = new Todo("1", "Todo 1","OPEN");;
        when(repoBackend.aktuTodo("1", expectedTodo)).thenReturn(expectedTodo);

        Todo todo = serviceBackend.AktuTodo("1", expectedTodo);

        assertEquals(expectedTodo, todo);
    }

    @Test
    public void testDeleteTodo() {
        serviceBackend.deleteTodo("1");

       verify(repoBackend).deleteTodo("1");
    }
}