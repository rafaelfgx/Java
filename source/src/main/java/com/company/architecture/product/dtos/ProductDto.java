package com.company.architecture.product.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
    UUID id,
    String description,
    BigDecimal price) {
}
