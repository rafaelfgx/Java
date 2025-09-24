package com.company.architecture.aws;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final S3Template s3Template;

    public S3Resource store(final String bucket, final String key, final Object object) {
        return s3Template.store(bucket, key, object);
    }

    public <T> T read(final String bucket, final String key, final Class<T> clazz) {
        return s3Template.read(bucket, key, clazz);
    }

    public S3Resource upload(final String bucket, final MultipartFile file) throws IOException {
        return upload(bucket, file.getOriginalFilename(), file.getBytes());
    }

    public S3Resource upload(final String bucket, final String key, final byte[] bytes) throws IOException {
        return s3Template.upload(bucket, key, new ByteArrayInputStream(bytes), ObjectMetadata.builder().contentType(Files.probeContentType(Paths.get(key))).build());
    }

    public S3Resource download(final String bucket, final String key) {
        return s3Template.download(bucket, key);
    }
}
