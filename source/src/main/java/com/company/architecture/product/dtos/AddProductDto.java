package com.company.architecture.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductDto {
    @NotBlank
    private String description;

    @Min(0L)
    private BigDecimal price;
}
