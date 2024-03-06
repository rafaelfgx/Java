package architecture.auth;

import architecture.Data;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;

@SpringBootTest(classes = JwtService.class)
class JwtServiceTest {
    @Autowired
    JwtService jwtService;

    @ParameterizedTest
    @NullAndEmptySource
    void testVerifyShouldAssertFalse(String token) {
        Assertions.assertFalse(jwtService.verify(token));
    }

    @Test
    void testVerifyShouldAssertFalse() {
        Assertions.assertFalse(jwtService.verify(UUID.randomUUID().toString()));
    }

    @Test
    void testVerifyShouldAssertTrue() {
        Assertions.assertTrue(jwtService.verify(jwtService.create(Data.user)));
    }

    @Test
    void testGetSubjectShouldAssertNotEquals() {
        Assertions.assertNotEquals("Invalid", jwtService.getSubject(jwtService.create(Data.user)));
    }

    @Test
    void testGetSubjectShouldAssertNotNull() {
        Assertions.assertNotNull(jwtService.getSubject(jwtService.create(Data.user)));
    }

    @Test
    void testGetAuthoritiesShouldAssertNotEquals() {
        Assertions.assertNotEquals(AuthorityUtils.createAuthorityList("Invalid"), jwtService.getAuthorities(jwtService.create(Data.user)));
    }

    @Test
    void testGetAuthoritiesShouldAssertEquals() {
        Assertions.assertEquals(AuthorityUtils.createAuthorityList("ADMINISTRATOR"), jwtService.getAuthorities(jwtService.create(Data.user)));
    }
}
