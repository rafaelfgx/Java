package com.company.architecture.user;

import com.company.architecture.ControllerTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class UserControllerTest extends ControllerTest {
    @MockitoBean
    UserService userService;

    private static Stream<Arguments> parameters() {
        return Stream.of(
            arguments("?page=-1", "[page: must be greater than or equal to 0]"),
            arguments("?page=9999999999", "[page: must be valid]"),
            arguments("?page=X", "[page: must be valid]"),
            arguments("?size=0", "[size: must be greater than 0]"),
            arguments("?size=9999999999", "[size: must be valid]"),
            arguments("?size=X", "[size: must be valid]"),
            arguments("?direction=X", "[direction: must be valid]"),
            arguments("/1", "[id: must be valid]"),
            arguments("/X", "[id: must be valid]")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void shouldReturnBadRequestWhenGettingUsers(String uri, String message) throws Exception {
        perform(GET, "/users" + uri).andExpectAll(status().isBadRequest(), content().string(message));
    }
}
