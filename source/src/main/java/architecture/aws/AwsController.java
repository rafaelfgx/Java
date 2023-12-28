package architecture.aws;

import architecture.shared.swagger.GetApiResponses;
import architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "AWS")
@RequiredArgsConstructor
@RestController
@RequestMapping("/aws")
public class AwsController {
    private final AwsSqsService awsSqsService;
    private final AwsS3Service awsS3Service;

    @Value("${spring.cloud.aws.sqs.queue}")
    private String queue;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Operation(summary = "Send")
    @PostApiResponses
    @PostMapping("queues/send")
    public void send(@RequestBody final String message) {
        awsSqsService.send(queue, message);
    }

    @Operation(summary = "Upload")
    @PostApiResponses
    @PostMapping(value = "files/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return awsS3Service.upload(bucket, file).getFilename();
    }

    @Operation(summary = "Download")
    @GetApiResponses
    @GetMapping("files/download/{key}")
    public ResponseEntity<Resource> get(@PathVariable final String key) {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(awsS3Service.download(bucket, key));
    }
}
