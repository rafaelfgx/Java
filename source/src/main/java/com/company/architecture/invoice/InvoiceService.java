package com.company.architecture.invoice;

import com.company.architecture.invoice.dtos.AddInvoiceDto;
import com.company.architecture.invoice.dtos.InvoiceDto;
import com.company.architecture.invoice.entities.Invoice;
import com.company.architecture.shared.services.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final MapperService mapperService;
    private final InvoiceRepository invoiceRepository;

    public List<InvoiceDto> get() {
        return mapperService.mapList(invoiceRepository.findAll(), InvoiceDto.class);
    }

    @Transactional
    public InvoiceDto add(final AddInvoiceDto dto) {
        return mapperService.map(invoiceRepository.save(mapperService.map(dto, Invoice.class)), InvoiceDto.class);
    }
}
