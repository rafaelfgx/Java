package architecture.aws;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

@Generated
@RequiredArgsConstructor
@Configuration
public class AwsConfiguration {
    private final Environment environment;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return isLocalOrTestProfile() ? buildSqsAsyncClient() : SqsAsyncClient.create();
    }

    @Bean
    public S3Client s3Client() {
        return isLocalOrTestProfile() ? buildS3Client() : S3Client.create();
    }

    private boolean isLocalOrTestProfile() {
        return Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> profile.equals("local") || profile.equals("test"));
    }

    private Region region() {
        return Region.of(Objects.requireNonNull(environment.getProperty("spring.cloud.aws.region.static")));
    }

    private URI endpoint() {
        return URI.create(Objects.requireNonNull(environment.getProperty("spring.cloud.aws.endpoint")));
    }

    private AwsCredentials credentials() {
        return AwsBasicCredentials.create(environment.getProperty("spring.cloud.aws.credentials.access-key"), environment.getProperty("spring.cloud.aws.credentials.secret-key"));
    }

    private SqsAsyncClient buildSqsAsyncClient() {
        final var client = SqsAsyncClient.builder().region(region()).endpointOverride(endpoint()).credentialsProvider(this::credentials).build();
        client.createQueue(builder -> builder.queueName(environment.getProperty("spring.cloud.aws.sqs.queue")));
        return client;
    }

    private S3Client buildS3Client() {
        final var client = S3Client.builder().region(region()).endpointOverride(endpoint()).credentialsProvider(this::credentials).serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build()).build();
        client.createBucket(builder -> builder.bucket(environment.getProperty("spring.cloud.aws.s3.bucket")));
        return client;
    }
}
