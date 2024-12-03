package com.company.architecture.aws;

import com.company.architecture.shared.swagger.GetApiResponses;
import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "AWS")
@RequiredArgsConstructor
@RestController
@RequestMapping("/aws")
public class AwsController {
    private final AwsService awsService;

    @Operation(summary = "Send")
    @PostApiResponses
    @PostMapping("queues/send")
    public void send(@RequestBody final String message) {
        awsService.send(message);
    }

    @Operation(summary = "Upload")
    @PostApiResponses
    @PostMapping(value = "files/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return awsService.upload(file).getFilename();
    }

    @Operation(summary = "Download")
    @GetApiResponses
    @GetMapping("files/download/{key}")
    public ResponseEntity<Resource> get(@PathVariable final String key) {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(awsService.download(key));
    }
}
