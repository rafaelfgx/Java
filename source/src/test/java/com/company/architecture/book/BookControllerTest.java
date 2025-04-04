package com.company.architecture.book;

import com.company.architecture.ControllerTest;
import com.company.architecture.book.create.CreateBookRequest;
import com.company.architecture.shared.mediator.Mediator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class BookControllerTest extends ControllerTest {
    @MockitoBean
    Mediator mediator;

    private static Stream<Arguments> parameters() {
        return Stream.of(
            arguments(new CreateBookRequest(null, null), "[author: must not be blank, title: must not be blank]"),
            arguments(new CreateBookRequest("", ""), "[author: must not be blank, title: must not be blank]"),
            arguments(new CreateBookRequest(null, "Author"), "[title: must not be blank]"),
            arguments(new CreateBookRequest("", "Author"), "[title: must not be blank]"),
            arguments(new CreateBookRequest("Title", null), "[author: must not be blank]"),
            arguments(new CreateBookRequest("Title", ""), "[author: must not be blank]")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void shouldReturnBadRequestWhenCreatingBook(CreateBookRequest request, String expectedErrors) throws Exception {
        perform(POST, "/books", request).andExpectAll(status().isBadRequest()).andExpect(content().string(containsString(expectedErrors)));
    }
}
