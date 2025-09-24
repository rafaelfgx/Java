package com.company.architecture.book;

import com.company.architecture.book.create.CreateBookHandler;
import com.company.architecture.book.create.CreateBookRequest;
import com.company.architecture.shared.mediator.Mediator;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(classes = {Mediator.class, CreateBookHandler.class})
class CreateBookHandlerMediatorTest {
    @Autowired
    Mediator mediator;

    @Test
    void shouldThrowConstraintViolationException() {
        final var exception = assertThrows(ConstraintViolationException.class, () -> mediator.handle(new CreateBookRequest("", ""), UUID.class));
        Assertions.assertEquals(2, exception.getConstraintViolations().size());
    }

    @Test
    void shouldReturnCreated() {
        final var responseEntity = mediator.handle(new CreateBookRequest("Title", "Author"), UUID.class);
        Assertions.assertEquals(CREATED, responseEntity.getStatusCode());
    }
}
