package com.company.architecture;

import com.company.architecture.shared.services.MapperService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.kafka.KafkaContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class IntegrationTest extends ControllerTest {
    @Container
    protected static final KafkaContainer kafka = KafkaTest.kafka;

    @Container
    static final LocalStackContainer localstack = LocalStackTest.localstack;

    @ServiceConnection
    static final MongoDBContainer mongo = MongoTest.mongo;

    @ServiceConnection
    static final PostgreSQLContainer<?> postgre = PostgreTest.postgre;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MapperService mapperService;

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        KafkaTest.registry(kafka, registry);
        LocalStackTest.registry(localstack, registry);
    }

    @BeforeAll
    static void beforeAll() {
        KafkaTest.beforeAll();
        LocalStackTest.beforeAll();
        MongoTest.beforeAll();
        PostgreTest.beforeAll();
    }

    @BeforeEach
    void before() {
        mongoTemplate.getDb().drop();
    }
}