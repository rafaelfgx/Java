package com.company.architecture.product.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductDto(
    @Schema(hidden = true) UUID id,
    @NotBlank String description,
    @Min(0L) BigDecimal price) {

    public UpdateProductDto withId(UUID id) {
        return new UpdateProductDto(id, description, price);
    }
}
