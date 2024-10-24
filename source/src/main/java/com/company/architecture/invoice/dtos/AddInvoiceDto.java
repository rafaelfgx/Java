package com.company.architecture.invoice.dtos;

import com.company.architecture.invoice.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AddInvoiceDto {
    @NotNull
    @Size(min = 1, max = 50)
    private String number;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private InvoiceStatus status;

    private List<AddInvoiceItemDto> items;
}
