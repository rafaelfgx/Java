package architecture.auth;

import architecture.IntegrationTest;
import architecture.Data;
import architecture.user.UserRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@MockBean(UserRepository.class)
class AuthIntegrationTest extends IntegrationTest {
    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @MethodSource("parameters")
    void testPostShouldReturnHttpStatus(AuthDto authDto, HttpStatusCode statusCode) throws Exception {
        Mockito.when(userRepository.findByUsername(Data.user.getUsername())).thenReturn(Optional.of(Data.user));
        post("/auth", authDto).andExpectAll(MockMvcResultMatchers.status().is(statusCode.value()));
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
            Arguments.arguments(null, HttpStatus.BAD_REQUEST),
            Arguments.arguments(new AuthDto(null, null), HttpStatus.BAD_REQUEST),
            Arguments.arguments(new AuthDto("", ""), HttpStatus.BAD_REQUEST),
            Arguments.arguments(new AuthDto(UUID.randomUUID().toString(), UUID.randomUUID().toString()), HttpStatus.UNAUTHORIZED),
            Arguments.arguments(new AuthDto(Data.user.getUsername(), UUID.randomUUID().toString()), HttpStatus.UNAUTHORIZED),
            Arguments.arguments(new AuthDto(UUID.randomUUID().toString(), Data.password), HttpStatus.UNAUTHORIZED),
            Arguments.arguments(new AuthDto(Data.user.getUsername(), Data.password), HttpStatus.OK)
        );
    }
}
