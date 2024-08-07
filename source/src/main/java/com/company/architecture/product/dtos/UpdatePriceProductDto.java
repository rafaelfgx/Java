package com.company.architecture.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
public class UpdatePriceProductDto {
    @NotNull
    private UUID id;

    @Min(0L)
    private BigDecimal price;
}
