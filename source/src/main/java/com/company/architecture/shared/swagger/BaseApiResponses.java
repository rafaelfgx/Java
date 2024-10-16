package com.company.architecture.shared.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@ApiResponse(responseCode = "400", content = @Content)
@ApiResponse(responseCode = "401", content = @Content)
@ApiResponse(responseCode = "403", content = @Content)
@ApiResponse(responseCode = "404", content = @Content)
@ApiResponse(responseCode = "422", content = @Content)
@ApiResponse(responseCode = "500", content = @Content)
@ApiResponse(responseCode = "503", content = @Content)
public @interface BaseApiResponses {
}
