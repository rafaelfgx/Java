package com.company.architecture.book;

import com.company.architecture.WebMvcTestConfiguration;
import com.company.architecture.book.create.CreateBookRequest;
import com.company.architecture.shared.mediator.Mediator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Import(WebMvcTestConfiguration.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    Mediator mediator;

    @ParameterizedTest
    @MethodSource
    void shouldReturnBadRequestWhenCreatingBook(CreateBookRequest request, String expectedErrors) throws Exception {
        mockMvc
            .perform(post("/books").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpectAll(status().isBadRequest()).andExpect(content().string(containsString(expectedErrors)));
    }

    static Stream<Arguments> shouldReturnBadRequestWhenCreatingBook() {
        return Stream.of(
            arguments(new CreateBookRequest(null, null), "[author: must not be blank, title: must not be blank]"),
            arguments(new CreateBookRequest("", ""), "[author: must not be blank, title: must not be blank]"),
            arguments(new CreateBookRequest(null, "Author"), "[title: must not be blank]"),
            arguments(new CreateBookRequest("", "Author"), "[title: must not be blank]"),
            arguments(new CreateBookRequest("Title", null), "[author: must not be blank]"),
            arguments(new CreateBookRequest("Title", ""), "[author: must not be blank]")
        );
    }
}
