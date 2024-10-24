package com.company.architecture.shared.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@BaseApiResponses
@ApiResponse(responseCode = "201")
@ApiResponse(responseCode = "202", content = @Content)
@ApiResponse(responseCode = "204", content = @Content)
@ResponseStatus(HttpStatus.CREATED)
public @interface PostApiResponses {
}
