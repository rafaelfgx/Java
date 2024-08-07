package com.company.architecture.invoice.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddInvoiceItemDto {
    @NotNull
    @Size(min = 1, max = 100)
    private String product;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal unitPrice;
}
