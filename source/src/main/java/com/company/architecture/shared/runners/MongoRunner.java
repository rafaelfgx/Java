package com.company.architecture.shared.runners;

import com.company.architecture.auth.Authority;
import com.company.architecture.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class MongoRunner implements ApplicationRunner {
    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder passwordEncoder;

    public void run(final ApplicationArguments args) {
        if (!mongoTemplate.collectionExists("user")) {
            mongoTemplate.save(new User(UUID.randomUUID(), "Admin", "admin@mail.com", "admin", passwordEncoder.encode("123456"), List.of(Authority.ADMINISTRATOR)));
        }
    }
}
