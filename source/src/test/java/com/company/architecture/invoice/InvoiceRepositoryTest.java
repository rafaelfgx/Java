package com.company.architecture.invoice;

import com.company.architecture.PostgreTest;
import com.company.architecture.invoice.entities.Invoice;
import com.company.architecture.invoice.entities.InvoiceItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class InvoiceRepositoryTest extends PostgreTest {
    @Autowired
    InvoiceRepository invoiceRepository;

    @RepeatedTest(2)
    void shouldPersistInvoice() {
        final var item = InvoiceItem.builder()
            .product("Ball")
            .quantity(BigDecimal.valueOf(2))
            .unitPrice(BigDecimal.valueOf(100))
            .build();

        var invoice = Invoice.builder()
            .number("123")
            .dateTime(LocalDateTime.now())
            .status(InvoiceStatus.ISSUED)
            .items(List.of(item))
            .build();

        invoice = testEntityManager.persist(invoice);

        Assertions.assertEquals(1, invoiceRepository.findAll().size());
        Assertions.assertEquals(1, invoice.getId());
    }
}
