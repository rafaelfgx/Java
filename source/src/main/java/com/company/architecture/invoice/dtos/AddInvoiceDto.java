package com.company.architecture.invoice.dtos;

import com.company.architecture.invoice.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record AddInvoiceDto(
    @NotNull @Size(min = 1, max = 50) String number,
    @NotNull LocalDateTime dateTime,
    @NotNull InvoiceStatus status,
    List<AddInvoiceItemDto> items) {
}
