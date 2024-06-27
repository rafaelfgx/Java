package architecture.aws;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsSqsService {
    private final SqsTemplate sqsTemplate;

    public void send(final String queue, final Object object) {
        sqsTemplate.send(queue, object);
    }

    @SqsListener("${spring.cloud.aws.sqs.queue}")
    public void listen(final Object object) {
        System.out.printf("AwsSqsService -> %s %n", object);
    }
}
