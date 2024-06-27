package architecture.invoice;

import architecture.IntegrationTest;
import architecture.invoice.dtos.AddInvoiceDto;
import architecture.invoice.dtos.AddInvoiceItemDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InvoiceIntegrationTest extends IntegrationTest {
    @Test
    void testGetShouldReturnNotFound() throws Exception {
        post("/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
        get("/invoices").andExpectAll(status().isOk());
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        post("/invoices", addInvoiceDto()).andExpectAll(status().isCreated());
    }

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
}
