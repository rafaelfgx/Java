package com.company.architecture.invoice;

import com.company.architecture.SpringBootTestConfiguration;
import com.company.architecture.invoice.dtos.AddInvoiceDto;
import com.company.architecture.invoice.dtos.AddInvoiceItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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
        mockMvc
            .perform(post("/invoices").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(addInvoiceDto())))
            .andExpectAll(status().isCreated());

        mockMvc.perform(get("/invoices")).andExpectAll(status().isOk());
    }

    @Test
    void shouldReturnCreatedWhenPostingInvoice() throws Exception {
        mockMvc
            .perform(post("/invoices").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(addInvoiceDto())))
            .andExpectAll(status().isCreated());
    }
}
