package com.company.architecture.aws;

import com.company.architecture.SpringBootTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwsSqsServiceTest {
    @Autowired
    AwsSqsService awsSqsService;

    @Test
    void shouldNotThrowExceptionWhenSendingMessageToQueue() {
        Assertions.assertDoesNotThrow(() -> awsSqsService.send(AwsConfiguration.QUEUE, "Message"));
    }
}
