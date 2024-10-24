package com.company.architecture.product.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateProductDto {
    @Schema(hidden = true)
    private UUID id;

    @NotBlank
    private String description;

    @Min(0L)
    private BigDecimal price;
}
