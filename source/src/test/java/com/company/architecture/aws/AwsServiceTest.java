package com.company.architecture.aws;

import com.company.architecture.SpringBootTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwsServiceTest {
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
