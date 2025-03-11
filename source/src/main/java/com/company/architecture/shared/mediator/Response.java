package com.company.architecture.shared.mediator;

import org.springframework.http.HttpStatus;

public record Response<Body>(HttpStatus status, Body body) {
    public Response(HttpStatus status) {
        this(status, null);
    }
}
