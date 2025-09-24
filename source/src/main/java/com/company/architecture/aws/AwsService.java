package com.company.architecture.aws;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsService {
    private final AwsSqsService awsSqsService;
    private final AwsS3Service awsS3Service;

    @SqsListener(AwsConfiguration.QUEUE)
    public void listen(final Object object) {
        log.info("[AwsSqsService].[listen]: {}", object);
    }

    public void send(final Object object) {
        awsSqsService.send(AwsConfiguration.QUEUE, object);
    }

    public S3Resource upload(final MultipartFile file) throws IOException {
        return awsS3Service.upload(AwsConfiguration.BUCKET, file.getOriginalFilename(), file.getBytes());
    }

    public S3Resource download(final String key) {
        return awsS3Service.download(AwsConfiguration.BUCKET, key);
    }
}
