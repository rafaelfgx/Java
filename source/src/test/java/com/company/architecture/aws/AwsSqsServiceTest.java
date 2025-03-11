package com.company.architecture.aws;

import com.company.architecture.LocalStackTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {AwsConfiguration.class, AwsSqsService.class})
class AwsSqsServiceTest extends LocalStackTest {
    @Autowired
    AwsSqsService awsSqsService;

    @Value("${spring.cloud.aws.sqs.queue}")
    private String queue;

    @Test
    void shouldNotThrowExceptionWhenSendingMessageToQueue() {
        Assertions.assertDoesNotThrow(() -> awsSqsService.send(queue, "Message"));
    }
}
