package com.company.architecture.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdatePriceProductDto(
    @NotNull UUID id,
    @Min(0L) BigDecimal price) {
}
