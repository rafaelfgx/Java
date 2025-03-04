package com.company.architecture.book;

import com.company.architecture.book.create.CreateBookHandler;
import com.company.architecture.book.create.CreateBookRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(classes = {CreateBookHandler.class})
class CreateBookHandlerTest {
    @Autowired
    CreateBookHandler createBookHandler;

    @Test
    void shouldReturnCreated() {
        final var response = createBookHandler.handle(new CreateBookRequest("Title", "Author"));
        Assertions.assertEquals(CREATED, response.status());
    }
}
