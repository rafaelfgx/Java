package com.company.architecture.shared.exception;

import com.company.architecture.shared.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private final HttpStatus status;

    public ApplicationException(HttpStatus status, String message) {
        super(MessageService.get(message));
        this.status = status;
    }
}
