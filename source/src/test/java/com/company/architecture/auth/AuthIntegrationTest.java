package com.company.architecture.auth;

import com.company.architecture.IntegrationTest;
import com.company.architecture.shared.Data;
import com.company.architecture.user.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthIntegrationTest extends IntegrationTest {
    @MockitoBean
    UserRepository userRepository;

    private static Stream<Arguments> parameters() {
        return Stream.of(
            arguments(null, HttpStatus.BAD_REQUEST),
            arguments(new AuthDto(null, null), HttpStatus.BAD_REQUEST),
            arguments(new AuthDto("", ""), HttpStatus.BAD_REQUEST),
            arguments(new AuthDto(UUID.randomUUID().toString(), UUID.randomUUID().toString()), HttpStatus.UNAUTHORIZED),
            arguments(new AuthDto(Data.USER.getUsername(), UUID.randomUUID().toString()), HttpStatus.UNAUTHORIZED),
            arguments(new AuthDto(UUID.randomUUID().toString(), Data.PASSWORD), HttpStatus.UNAUTHORIZED),
            arguments(new AuthDto(Data.USER.getUsername(), Data.PASSWORD), HttpStatus.OK)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void shouldReturnHttpStatus(AuthDto authDto, HttpStatusCode statusCode) throws Exception {
        when(userRepository.findByUsername(Data.USER.getUsername())).thenReturn(Optional.of(Data.USER));
        perform(POST, "/auth", authDto).andExpectAll(status().is(statusCode.value()));
    }
}
