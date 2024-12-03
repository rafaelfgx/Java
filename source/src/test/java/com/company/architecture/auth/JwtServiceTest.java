package com.company.architecture.auth;

import com.company.architecture.shared.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.UUID;

@SpringBootTest(classes = JwtService.class)
class JwtServiceTest {
    @Autowired
    JwtService jwtService;

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnFalseWhenTokenIsNullOrEmpty(String token) {
        Assertions.assertFalse(jwtService.verify(token));
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        Assertions.assertFalse(jwtService.verify(UUID.randomUUID().toString()));
    }

    @Test
    void shouldReturnTrueForValidToken() {
        Assertions.assertTrue(jwtService.verify(jwtService.create(Data.USER)));
    }

    @Test
    void shouldNotReturnInvalidSubjectForValidToken() {
        Assertions.assertNotEquals("Invalid", jwtService.getSubject(jwtService.create(Data.USER)));
    }

    @Test
    void shouldReturnNonNullSubjectForValidToken() {
        Assertions.assertNotNull(jwtService.getSubject(jwtService.create(Data.USER)));
    }

    @Test
    void shouldNotReturnInvalidAuthoritiesForValidToken() {
        Assertions.assertNotEquals(AuthorityUtils.createAuthorityList("Invalid"), jwtService.getAuthorities(jwtService.create(Data.USER)));
    }

    @Test
    void shouldReturnCorrectAuthoritiesForValidToken() {
        Assertions.assertEquals(AuthorityUtils.createAuthorityList("ADMINISTRATOR"), jwtService.getAuthorities(jwtService.create(Data.USER)));
    }
}
