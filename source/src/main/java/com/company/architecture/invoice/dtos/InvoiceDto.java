package com.company.architecture.invoice.dtos;

import com.company.architecture.invoice.InvoiceStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
    private Long id;
    private String number;
    private LocalDateTime dateTime;
    private InvoiceStatus status;
    private List<InvoiceItemDto> items;
}
