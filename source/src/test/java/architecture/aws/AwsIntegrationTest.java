package architecture.aws;

import architecture.IntegrationTest;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AwsIntegrationTest extends IntegrationTest {
    @Test
    void testSend() throws Exception {
        post("/aws/queues/send", "Message").andExpectAll(status().isCreated());
    }

    @Test
    void testUploadDownload() throws Exception {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        multipart("/aws/files/upload", file).andExpectAll(status().isCreated());
        get("/aws/files/download/Test.txt").andExpectAll(status().isOk());
    }
}
