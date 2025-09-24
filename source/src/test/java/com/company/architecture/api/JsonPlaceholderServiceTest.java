package com.company.architecture.api;

import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootTest(classes = {FeignAutoConfiguration.class, JacksonAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class, JsonPlaceholderService.class})
@EnableFeignClients
class JsonPlaceholderServiceTest {
    @Autowired
    JsonPlaceholderService jsonPlaceholderService;

    @Test
    void shouldReturnNotNullWhenGetTodos() {
        final var list = jsonPlaceholderService.getTodos();
        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void shouldReturnNotNullWhenGetTodo() {
        Assertions.assertNotNull(jsonPlaceholderService.getTodo(1));
    }

    @Test
    void shouldThrowExceptionWhenGetTodo() {
        Assertions.assertThrows(FeignException.class, () -> jsonPlaceholderService.getTodo(99999));
    }
}
