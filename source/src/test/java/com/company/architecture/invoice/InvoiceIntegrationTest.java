package com.company.architecture.invoice;

import com.company.architecture.IntegrationTest;
import com.company.architecture.invoice.dtos.AddInvoiceDto;
import com.company.architecture.invoice.dtos.AddInvoiceItemDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InvoiceIntegrationTest extends IntegrationTest {
    static AddInvoiceDto addInvoiceDto() {
        final var addInvoiceItemDto = new AddInvoiceItemDto();
        addInvoiceItemDto.setProduct("Product");
        addInvoiceItemDto.setQuantity(2);
        addInvoiceItemDto.setUnitPrice(BigDecimal.valueOf(250));
        final var addInvoiceDto = new AddInvoiceDto();
        addInvoiceDto.setNumber(UUID.randomUUID().toString());
        addInvoiceDto.setDateTime(LocalDateTime.now());
        addInvoiceDto.setStatus(InvoiceStatus.DRAFT);
        addInvoiceDto.setItems(List.of(addInvoiceItemDto));
        return addInvoiceDto;
    }

    @Test
    void testGetShouldReturnNotFound() throws Exception {
        perform(POST, "/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
        perform(GET, "/invoices").andExpectAll(status().isOk());
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        perform(POST, "/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
    }
}
