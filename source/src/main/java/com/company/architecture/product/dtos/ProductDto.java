package com.company.architecture.product.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String description;
    private BigDecimal price;
}
