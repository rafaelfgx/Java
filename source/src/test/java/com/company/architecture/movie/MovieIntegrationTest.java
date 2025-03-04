package com.company.architecture.movie;

import com.company.architecture.IntegrationTest;
import org.junit.jupiter.api.Test;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieIntegrationTest extends IntegrationTest {
    @Test
    void shouldReturnOkWhenCreatingMovie() throws Exception {
        perform(POST, "/movies", new AddMovieDto("Movie")).andExpectAll(status().isOk());
    }
}
