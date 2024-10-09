package com.company.architecture.shared.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handle(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handle(final AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handle(final NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handle(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[%s: must be valid]".formatted(exception.getPropertyName()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(final MethodArgumentNotValidException exception) {
        final List<String> errors = new ArrayList<>();

        for (final var error : exception.getBindingResult().getFieldErrors()) {
            var message = error.getDefaultMessage();

            if (Arrays.asList(Objects.requireNonNull(error.getCodes())).contains("typeMismatch")) {
                message = "must be valid";
            }

            errors.add("%s: %s".formatted(error.getField(), message));
        }

        for (final var error : exception.getBindingResult().getGlobalErrors()) {
            errors.add("%s: %s".formatted(error.getObjectName(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().sorted().toList().toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(final HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handle(final ApplicationException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
