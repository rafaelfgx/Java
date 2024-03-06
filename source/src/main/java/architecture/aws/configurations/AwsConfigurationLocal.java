package architecture.aws.configurations;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Profile("local || test")
@Configuration
public class AwsConfigurationLocal {
    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Value("${spring.cloud.aws.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.aws.sqs.queue}")
    private String queue;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        final var sqsAsyncClient = SqsAsyncClient
            .builder()
            .region(Region.of(region))
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
            .build();

        sqsAsyncClient.createQueue(builder -> builder.queueName(queue));

        return sqsAsyncClient;
    }

    @Bean
    public S3Client s3Client() {
        final var s3Client = S3Client
            .builder()
            .region(Region.of(region))
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
            .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
            .build();

        s3Client.createBucket(builder -> builder.bucket(bucket));

        return s3Client;
    }
}
