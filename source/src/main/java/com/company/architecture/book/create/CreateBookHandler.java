package com.company.architecture.book.create;

import com.company.architecture.shared.mediator.Handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBookHandler implements Handler<CreateBookRequest, UUID> {
    @Override
    public ResponseEntity<UUID> handle(CreateBookRequest request) {
        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }
}
