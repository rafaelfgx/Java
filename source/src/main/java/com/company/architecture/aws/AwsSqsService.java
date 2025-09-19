package com.company.architecture.aws;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsSqsService {
    private final SqsTemplate sqsTemplate;

    public void send(final String queue, final Object object) {
        sqsTemplate.send(queue, object);
    }
}
