package com.company.architecture.notification;

import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Notifications")
@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "Send")
    @PostApiResponses
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestBody @Valid final Notification notification) {
        notificationService.send(notification);
    }
}
