package com.company.architecture.notification;

import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notifications")
@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationProducer notificationProducer;

    @Operation(summary = "Produce")
    @PostApiResponses
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void produce() {
        notificationProducer.produce();
    }
}
