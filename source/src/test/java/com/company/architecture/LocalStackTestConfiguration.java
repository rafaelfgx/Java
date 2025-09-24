package com.company.architecture;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class LocalStackTestConfiguration {
    @Bean
    LocalStackContainer localStackContainer() {
        final var container = new LocalStackContainer(DockerImageName.parse("localstack/localstack"));

        container.withServices(
            LocalStackContainer.Service.SQS,
            LocalStackContainer.EnabledService.named("sqs-query"),
            LocalStackContainer.Service.S3
        );

        container.start();

        System.setProperty("aws.endpoint", container.getEndpoint().toString());
        System.setProperty("aws.region", container.getRegion());
        System.setProperty("aws.accessKeyId", container.getAccessKey());
        System.setProperty("aws.secretAccessKey", container.getSecretKey());

        return container;
    }
}
