package com.company.architecture.book.create;

import com.company.architecture.shared.mediator.Handler;
import com.company.architecture.shared.mediator.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBookHandler implements Handler<CreateBookRequest, UUID> {
    @Override
    public Response<UUID> handle(CreateBookRequest request) {
        return new Response<>(HttpStatus.CREATED, UUID.randomUUID());
    }
}
