package com.company.architecture.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonPlaceholderService {
    private final JsonPlaceholderClient jsonPlaceholderClient;

    public List<Todo> getTodos() {
        return jsonPlaceholderClient.getTodos();
    }

    public Todo getTodo(final int id) {
        return jsonPlaceholderClient.getTodo(id);
    }
}
