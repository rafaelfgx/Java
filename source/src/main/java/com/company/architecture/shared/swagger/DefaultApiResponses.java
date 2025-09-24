package com.company.architecture.shared.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@BaseApiResponses
@ApiResponse(responseCode = "200", content = @Content)
@ApiResponse(responseCode = "202", content = @Content)
@ApiResponse(responseCode = "204", content = @Content)
public @interface DefaultApiResponses {
}
