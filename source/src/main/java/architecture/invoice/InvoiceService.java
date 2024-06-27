package architecture.invoice;

import architecture.invoice.dtos.AddInvoiceDto;
import architecture.invoice.dtos.InvoiceDto;
import architecture.invoice.entities.Invoice;
import architecture.shared.services.MapperService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final MapperService mapperService;
    private final InvoiceRepository invoiceRepository;

    public List<InvoiceDto> get() {
        return invoiceRepository.findAll().stream().map(entity -> mapperService.map(entity, InvoiceDto.class)).toList();
    }

    public InvoiceDto add(final AddInvoiceDto dto) {
        return mapperService.map(invoiceRepository.save(mapperService.map(dto, Invoice.class)), InvoiceDto.class);
    }
}
