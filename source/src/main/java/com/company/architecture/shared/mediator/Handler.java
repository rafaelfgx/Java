package com.company.architecture.shared.mediator;

public interface Handler<Request, Body> {
    Response<Body> handle(Request request);
}
