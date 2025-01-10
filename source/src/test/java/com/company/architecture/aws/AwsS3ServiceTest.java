package com.company.architecture.aws;

import com.company.architecture.LocalStackTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@SpringBootTest(classes = {AwsConfiguration.class, AwsS3Service.class})
class AwsS3ServiceTest extends LocalStackTest {
    @Autowired
    AwsS3Service awsS3Service;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void shouldThrowExceptionWhenStoringInInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.store("INVALID", "123", "Object"));
    }

    @Test
    void shouldNotThrowExceptionWhenStoringInValidBucket() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(bucket, "123", "Object"));
    }

    @Test
    void shouldThrowExceptionWhenReadingFromInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read("INVALID", "123", String.class));
    }

    @Test
    void shouldThrowExceptionWhenReadingWithInvalidKey() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read(bucket, "INVALID", String.class));
    }

    @Test
    void shouldNotThrowExceptionWhenReadingFromValidBucketAndKey() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(bucket, "123", "Object"));
        Assertions.assertDoesNotThrow(() -> awsS3Service.read(bucket, "123", String.class));
    }

    @Test
    void shouldNotThrowExceptionWhenUploadingFile() throws IOException {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, file));
    }

    @Test
    void shouldThrowExceptionWhenUploadingBytesToInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload("INVALID", "Test.txt", "Object".getBytes()));
    }

    @Test
    void shouldThrowExceptionWhenUploadingInvalidBytes() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload(bucket, "Test.txt", null));
    }

    @Test
    void shouldNotThrowExceptionWhenUploadingValidBytes() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, "Test.txt", "Object".getBytes()));
    }

    @Test
    void shouldNotThrowExceptionWhenDownloadingFile() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(bucket, "Test.txt", "Object".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.download(bucket, "Test.txt"));
    }
}
