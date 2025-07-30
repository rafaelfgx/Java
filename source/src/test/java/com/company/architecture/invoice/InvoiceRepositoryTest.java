package com.company.architecture.invoice;

import com.company.architecture.PostgreTest;
import com.company.architecture.invoice.entities.Invoice;
import com.company.architecture.invoice.entities.InvoiceItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

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

        final var invoice = Invoice.builder()
            .number("123")
            .status(InvoiceStatus.ISSUED)
            .build()
            .setItems(Set.of(item));

        final var persistedInvoice = testEntityManager.persist(invoice);
        final var originalItem = invoice.getItems().iterator().next();
        final var persistedItem = persistedInvoice.getItems().iterator().next();

        Assertions.assertEquals(1, persistedInvoice.getId());
        Assertions.assertEquals(invoice.getNumber(), persistedInvoice.getNumber());
        Assertions.assertEquals(invoice.getDateTime(), persistedInvoice.getDateTime());
        Assertions.assertEquals(invoice.getStatus(), persistedInvoice.getStatus());

        Assertions.assertEquals(originalItem.getProduct(), persistedItem.getProduct());
        Assertions.assertEquals(originalItem.getQuantity(), persistedItem.getQuantity());
        Assertions.assertEquals(originalItem.getUnitPrice(), persistedItem.getUnitPrice());
        Assertions.assertEquals(originalItem.getInvoice(), persistedItem.getInvoice());
    }
}
