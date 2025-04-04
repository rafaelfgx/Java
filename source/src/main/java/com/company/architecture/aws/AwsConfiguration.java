package com.company.architecture.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class AwsConfiguration {
    private final Environment environment;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        final var client = SqsAsyncClient.builder().endpointOverride(endpoint()).build();
        client.createQueue(builder -> builder.queueName(environment.getProperty("spring.cloud.aws.sqs.queue")));
        return client;
    }

    @Bean
    public S3Client s3Client() {
        final var client = S3Client.builder().endpointOverride(endpoint()).serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build()).build();
        client.createBucket(builder -> builder.bucket(environment.getProperty("spring.cloud.aws.s3.bucket")));
        return client;
    }

    private URI endpoint() {
        return URI.create(Objects.requireNonNullElse(environment.getProperty("AWS_ENDPOINT"), environment.getProperty("aws.endpoint")));
    }
}
