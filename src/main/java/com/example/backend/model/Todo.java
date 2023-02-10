package com.example.backend.model;

import lombok.Data;

@Data
public class Todo {
    private String description;
    private String id;

    private String status;

    public Todo() {

    }

    public Todo(String description, String id, String status) {
        this.description = description;
        this.id = id;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    public Todo Td(String todoid)
    {
        this.setId(todoid);
        return this;
    }
}
