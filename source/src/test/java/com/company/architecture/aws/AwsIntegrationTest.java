package com.company.architecture.aws;

import com.company.architecture.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AwsIntegrationTest extends IntegrationTest {
    @Test
    void shouldReturnCreatedWhenSendingMessage() throws Exception {
        perform(POST, "/aws/queues/send", "Message").andExpectAll(status().isCreated());
    }

    @Test
    void shouldReturnOkWhenUploadingAndDownloadingFile() throws Exception {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        multipart("/aws/files/upload", file).andExpectAll(status().isCreated());
        perform(GET, "/aws/files/download/Test.txt").andExpectAll(status().isOk());
    }
}
