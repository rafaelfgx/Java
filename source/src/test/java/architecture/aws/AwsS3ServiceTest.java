package architecture.aws;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import architecture.LocalStackTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest(classes = {AwsConfiguration.class, AwsS3Service.class})
class AwsS3ServiceTest extends LocalStackTest {
    @Autowired
    AwsS3Service awsS3Service;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void testStoreWithInvalidBucketShouldThrow() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.store("INVALID", "123", "Object"));
    }

    @Test
    void testStoreShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(bucket, "123", "Object"));
    }

    @Test
    void testReadWithInvalidBucketShouldThrow() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read("INVALID", "123", String.class));
    }

    @Test
    void testReadWithInvalidKeyShouldThrow() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read(bucket, "INVALID", String.class));
    }

    @Test
    void testReadShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(bucket, "123", "Object"));
        Assertions.assertDoesNotThrow(() -> awsS3Service.read(bucket, "123", String.class));
    }

    @Test
    void testUploadFileShouldNotThrow() throws IOException {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, file));
    }

    @Test
    void testUploadBytesWithInvalidBucketShouldThrow() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload("INVALID", "123456.txt", "Object".getBytes()));
    }

    @Test
    void testUploadBytesWithInvalidBytesShouldThrow() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload(bucket, "123456.txt", null));
    }

    @Test
    void testUploadBytesShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, "123456.txt", "Object".getBytes()));
    }

    @Test
    void testDownloadShouldNotThrow() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, "123456.txt", "Object".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.download(bucket, "123456.txt"));
    }
}
