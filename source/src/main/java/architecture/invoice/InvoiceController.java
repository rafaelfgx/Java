package architecture.invoice;

import architecture.invoice.dtos.AddInvoiceDto;
import architecture.invoice.dtos.InvoiceDto;
import architecture.shared.swagger.GetApiResponses;
import architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
