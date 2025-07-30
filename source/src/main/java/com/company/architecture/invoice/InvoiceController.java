package com.company.architecture.invoice;

import com.company.architecture.invoice.dtos.AddInvoiceDto;
import com.company.architecture.invoice.dtos.InvoiceDto;
import com.company.architecture.shared.swagger.GetApiResponses;
import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Invoices")
@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Operation(summary = "Get")
    @GetApiResponses
    @GetMapping
    public List<InvoiceDto> get() {
        return invoiceService.get();
    }

    @Operation(summary = "Add")
    @PostApiResponses
    @PostMapping
    public InvoiceDto add(@RequestBody @Valid final AddInvoiceDto dto) {
        return invoiceService.add(dto);
    }
}
