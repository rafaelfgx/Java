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

@SpringBootTest(classes = {JwtConfiguration.class, JwtService.class})
class JwtServiceTest {
    @Autowired
    JwtService jwtService;

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnFalseWhenTokenIsNullOrEmpty(String token) {
        Assertions.assertFalse(jwtService.verify(token));
    }

    @Test
    void shouldReturnFalseWhenTokenIsInvalid() {
        Assertions.assertFalse(jwtService.verify(UUID.randomUUID().toString()));
    }

    @Test
    void shouldReturnTrueWhenTokenIsValid() {
        Assertions.assertTrue(jwtService.verify(jwtService.create(Data.USER)));
    }

    @Test
    void shouldNotReturnInvalidSubjectWhenTokenIsValid() {
        Assertions.assertNotEquals("Invalid", jwtService.getSubject(jwtService.create(Data.USER)));
    }

    @Test
    void shouldReturnNonNullSubjectWhenTokenIsValid() {
        Assertions.assertNotNull(jwtService.getSubject(jwtService.create(Data.USER)));
    }

    @Test
    void shouldNotReturnInvalidAuthoritiesWhenTokenIsValid() {
        Assertions.assertNotEquals(AuthorityUtils.createAuthorityList("Invalid"), jwtService.getAuthorities(jwtService.create(Data.USER)));
    }

    @Test
    void shouldReturnCorrectAuthoritiesWhenTokenIsValid() {
        Assertions.assertEquals(AuthorityUtils.createAuthorityList("ADMINISTRATOR"), jwtService.getAuthorities(jwtService.create(Data.USER)));
    }
}
