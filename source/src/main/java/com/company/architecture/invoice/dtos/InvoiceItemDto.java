package com.company.architecture.invoice.dtos;

import java.math.BigDecimal;

public record InvoiceItemDto(
    Long id,
    String product,
    Integer quantity,
    BigDecimal unitPrice) {
}
