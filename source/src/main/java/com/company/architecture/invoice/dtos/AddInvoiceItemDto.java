package com.company.architecture.invoice.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AddInvoiceItemDto(
    @NotNull @Size(min = 1, max = 100) String product,
    @NotNull @Min(value = 1) Integer quantity,
    @NotNull @DecimalMin(value = "0.01") BigDecimal unitPrice) {
}
