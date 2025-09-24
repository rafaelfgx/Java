package com.company.architecture.invoice.dtos;

import com.company.architecture.invoice.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.List;

public record InvoiceDto(
    Long id,
    String number,
    LocalDateTime dateTime,
    InvoiceStatus status,
    List<InvoiceItemDto> items) {
}
