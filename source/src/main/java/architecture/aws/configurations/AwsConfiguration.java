package architecture.aws.configurations;

import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Profile("!test && !local")
@Generated
@Configuration
public class AwsConfiguration {
    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.create();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.create();
    }
}
