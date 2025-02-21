package com.company.architecture.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JsonPlaceholderClientFallback implements JsonPlaceholderClient {
    @Override
    public List<Todo> getTodos() {
        log.info("[JsonPlaceholderClientFallback] getTodos");
        return List.of();
    }

    @Override
    public Todo getTodo(int id) {
        log.info("[JsonPlaceholderClientFallback] getTodo");
        return null;
    }
}
