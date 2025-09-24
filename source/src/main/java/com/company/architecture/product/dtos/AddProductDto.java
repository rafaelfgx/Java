package com.company.architecture.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AddProductDto(
    @NotBlank String description,
    @Min(0L) BigDecimal price) {
}
