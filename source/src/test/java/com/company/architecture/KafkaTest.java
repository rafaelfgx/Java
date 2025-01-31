package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.kafka.ConfluentKafkaContainer;

@DirtiesContext
public abstract class KafkaTest {
    @ServiceConnection
    protected static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer("confluentinc/cp-kafka");

    @BeforeAll
    static void beforeAll() {
        kafka.start();
    }
}
