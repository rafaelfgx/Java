package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.kafka.ConfluentKafkaContainer;

@DirtiesContext
public abstract class KafkaTest {
    @Container
    protected static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer("confluentinc/cp-kafka");

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry(kafka, registry);
    }

    @BeforeAll
    static void beforeAll() {
        kafka.start();
    }

    static void registry(ConfluentKafkaContainer container, DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
    }
}
