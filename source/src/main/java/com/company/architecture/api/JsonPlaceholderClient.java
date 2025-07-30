package com.company.architecture.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "${feign.clients.jsonplaceholder.url}", fallback = JsonPlaceholderClientFallback.class)
public interface JsonPlaceholderClient {
    @GetMapping("todos")
    List<Todo> getTodos();

    @GetMapping("todos/{id}")
    Todo getTodo(@PathVariable int id);
}
