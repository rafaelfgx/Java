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
        return new AddInvoiceDto(
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            InvoiceStatus.DRAFT,
            List.of(new AddInvoiceItemDto("Product", 2, BigDecimal.valueOf(250)))
        );
    }

    @Test
    void shouldReturnOkWhenGettingInvoices() throws Exception {
        perform(POST, "/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
        perform(GET, "/invoices").andExpectAll(status().isOk());
    }

    @Test
    void shouldReturnCreatedWhenPostingInvoice() throws Exception {
        perform(POST, "/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
    }
}
