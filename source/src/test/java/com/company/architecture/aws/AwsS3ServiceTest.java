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
class AwsS3ServiceTest {
    @Autowired
    AwsS3Service awsS3Service;

    @Test
    void shouldThrowExceptionWhenStoringInInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.store("INVALID", "123", "Object"));
    }

    @Test
    void shouldNotThrowExceptionWhenStoringInValidBucket() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(AwsConfiguration.BUCKET, "123", "Object"));
    }

    @Test
    void shouldThrowExceptionWhenReadingFromInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read("INVALID", "123", String.class));
    }

    @Test
    void shouldThrowExceptionWhenReadingWithInvalidKey() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.read(AwsConfiguration.BUCKET, "INVALID", String.class));
    }

    @Test
    void shouldNotThrowExceptionWhenReadingFromValidBucketAndKey() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.store(AwsConfiguration.BUCKET, "123", "Object"));
        Assertions.assertDoesNotThrow(() -> awsS3Service.read(AwsConfiguration.BUCKET, "123", String.class));
    }

    @Test
    void shouldNotThrowExceptionWhenUploadingFile() throws IOException {
        final var file = new MockMultipartFile("file", "Test.txt", "text/plain", new ByteArrayInputStream("Test".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(AwsConfiguration.BUCKET, file));
    }

    @Test
    void shouldThrowExceptionWhenUploadingBytesToInvalidBucket() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload("INVALID", "Test.txt", "Object".getBytes()));
    }

    @Test
    void shouldThrowExceptionWhenUploadingInvalidBytes() {
        Assertions.assertThrows(Exception.class, () -> awsS3Service.upload(AwsConfiguration.BUCKET, "Test.txt", null));
    }

    @Test
    void shouldNotThrowExceptionWhenUploadingValidBytes() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(AwsConfiguration.BUCKET, "Test.txt", "Object".getBytes()));
    }

    @Test
    void shouldNotThrowExceptionWhenDownloadingFile() {
        Assertions.assertDoesNotThrow(() -> awsS3Service.upload(AwsConfiguration.BUCKET, "Test.txt", "Object".getBytes()));
        Assertions.assertDoesNotThrow(() -> awsS3Service.download(AwsConfiguration.BUCKET, "Test.txt"));
    }
}
