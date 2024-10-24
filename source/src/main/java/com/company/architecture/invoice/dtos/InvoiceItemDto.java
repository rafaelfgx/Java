package com.company.architecture.invoice.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemDto {
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal unitPrice;
}
