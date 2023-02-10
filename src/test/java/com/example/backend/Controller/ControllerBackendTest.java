package com.example.backend.Controller;

import com.example.backend.Pepo.RepoBackend;
import com.example.backend.model.Status;
import com.example.backend.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerBackendTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    RepoBackend repoBackend;
    Todo todo1_noID, todo2_noID, todo1_withID, todo2_withID;

    @BeforeEach
    void setUp() {
        todo1_noID = new Todo(null, "My first item", Status.OPEN);
        todo2_noID = new Todo(null, "My second item", Status.OPEN);
        todo1_withID = new Todo("123", "My first item", Status.OPEN);
        todo2_withID = new Todo("234", "My second item", Status.OPEN);
    }

    @Test
    @DirtiesContext
    void addTodoItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"description": "fds", "status": "OPEN"}
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"description": "fds", "status": "OPEN"}
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void listTodoItems() throws Exception {
        // GIVEN
        repoBackend.addTodo(todo1_withID);
        repoBackend.addTodo(todo2_withID);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "123",
                                "description": "My first item",
                                "status": "OPEN"
                            },
                            {
                                "id": "234",
                                "description": "My second item",
                                "status": "OPEN"
                            }
                        ]
                        """));

        // THEN
    }

    @Test
    @DirtiesContext
    void getTodoItemById() throws Exception {
        // GIVEN
        repoBackend.addTodo(todo1_withID);
        repoBackend.addTodo(todo2_withID);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + todo1_withID.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "123",
                            "description": "My first item",
                            "status": "OPEN"
                        }
                        """));

        // THEN
    }

    @Test
    @DirtiesContext
    void updateTodoItem() throws Exception {
        // GIVEN
        repoBackend.addTodo(todo1_withID);
        repoBackend.addTodo(todo2_withID);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + todo1_withID.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "id": "123",
                                "description": "My first item EDITED",
                                "status": "IN_PROGRESS"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "123",
                            "description": "My first item EDITED",
                            "status": "IN_PROGRESS"
                        }
                        """));

        // THEN
    }

    @Test
    @DirtiesContext
    void deleteTodoItem() throws Exception {
        // GIVEN
        repoBackend.addTodo(todo1_withID);
        repoBackend.addTodo(todo2_withID);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + todo1_withID.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "123",
                            "description": "My first item",
                            "status": "OPEN"
                        }
                        """));

        // THEN
    }
}