package com.company.architecture.aws;

import com.company.architecture.LocalStackTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@SpringBootTest(classes = {AwsConfiguration.class, AwsSqsService.class, AwsS3Service.class, AwsService.class})
class AwsServiceTest extends LocalStackTest {
    @Autowired
    AwsService awsService;

    @Test
    void shouldNotThrowExceptionWhenSendingMessage() {
        Assertions.assertDoesNotThrow(() -> awsService.send("Message"));
    }

    @Test
    void shouldNotThrowExceptionWhenListeningForMessage() {
        Assertions.assertDoesNotThrow(() -> awsService.listen("Message"));
    }

    @Test
    void shouldNotThrowExceptionWhenUploadingAndDownloadingFile() throws IOException {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsService.upload(file));
        Assertions.assertDoesNotThrow(() -> awsService.download("Test.txt"));
    }
}
