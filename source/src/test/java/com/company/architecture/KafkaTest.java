package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class KafkaTest {
    @ServiceConnection
    protected static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("apache/kafka"))
        .withEnv("KAFKA_LISTENERS", "PLAINTEXT://:9092,BROKER://:9093,CONTROLLER://:9094");

    @BeforeAll
    static void beforeAll() {
        kafka.start();
    }
}
