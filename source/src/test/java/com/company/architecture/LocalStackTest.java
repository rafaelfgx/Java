package com.company.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public abstract class LocalStackTest {
    @Container
    protected static final LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
        .withServices(
            LocalStackContainer.Service.SQS,
            LocalStackContainer.EnabledService.named("sqs-query"),
            LocalStackContainer.Service.S3
        );

    @BeforeAll
    static void beforeAll() {
        localstack.start();
        System.setProperty("aws.endpoint", localstack.getEndpoint().toString());
        System.setProperty("aws.region", localstack.getRegion());
        System.setProperty("aws.accessKeyId", localstack.getAccessKey());
        System.setProperty("aws.secretAccessKey", localstack.getSecretKey());
    }
}
