package com.company.architecture.book;

import com.company.architecture.SpringBootTestConfiguration;
import com.company.architecture.book.create.CreateBookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnBadRequestWhenCreatingBook() throws Exception {
        mockMvc
            .perform(post("/books").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(new CreateBookRequest("", ""))))
            .andExpectAll(status().isBadRequest());
    }

    @Test
    void shouldReturnCreatedWhenCreatingBook() throws Exception {
        mockMvc
            .perform(post("/books").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(new CreateBookRequest("Title", "Author"))))
            .andExpectAll(status().isCreated());
    }
}
