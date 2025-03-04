package com.company.architecture.book;

import com.company.architecture.IntegrationTest;
import com.company.architecture.book.create.CreateBookRequest;
import org.junit.jupiter.api.Test;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookIntegrationTest extends IntegrationTest {
    @Test
    void shouldReturnBadRequestWhenCreatingBook() throws Exception {
        perform(POST, "/books", new CreateBookRequest("", "")).andExpectAll(status().isBadRequest());
    }

    @Test
    void shouldReturnCreatedWhenCreatingBook() throws Exception {
        perform(POST, "/books", new CreateBookRequest("Title", "Author")).andExpectAll(status().isCreated());
    }
}
