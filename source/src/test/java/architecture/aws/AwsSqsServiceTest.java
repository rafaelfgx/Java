package architecture.aws;

import architecture.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class AwsSqsServiceTest extends BaseTest {
    @Autowired
    AwsSqsService awsSqsService;

    @Value("${spring.cloud.aws.sqs.queue}")
    private String queue;

    @Test
    void testSendShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsSqsService.send(queue, "Message"));
    }

    @Test
    void testListenShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsSqsService.listen("Message"));
    }
}
