package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.kafka.KafkaContainer;

@DirtiesContext
public abstract class KafkaTest {
    @Container
    protected static final KafkaContainer kafka = new KafkaContainer("apache/kafka");

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry(kafka, registry);
    }

    @BeforeAll
    static void beforeAll() {
        kafka.start();
    }

    static void registry(KafkaContainer container, DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
    }
}
