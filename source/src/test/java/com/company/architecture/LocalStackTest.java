package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public abstract class LocalStackTest {
    @Container
    static final LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack")).withServices(LocalStackContainer.Service.SQS, LocalStackContainer.EnabledService.named("sqs-query"), LocalStackContainer.Service.S3);

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry(localstack, registry);
    }

    @BeforeAll
    static void beforeAll() {
        localstack.start();
    }

    static void registry(LocalStackContainer container, DynamicPropertyRegistry registry) {
        System.setProperty("aws.endpoint", container.getEndpoint().toString());
        System.setProperty("aws.region", container.getRegion());
        System.setProperty("aws.accessKeyId", container.getAccessKey());
        System.setProperty("aws.secretAccessKey", container.getSecretKey());
    }
}
