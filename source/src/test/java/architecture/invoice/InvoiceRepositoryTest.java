package architecture.invoice;

import architecture.PostgreTest;
import architecture.invoice.entities.Invoice;
import architecture.invoice.entities.InvoiceItem;
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
    void test1() {
        final var item = new InvoiceItem();
        item.setProduct("Ball");
        item.setQuantity(BigDecimal.valueOf(2));
        item.setUnitPrice(BigDecimal.valueOf(100));
        var invoice = new Invoice();
        invoice.setNumber("123");
        invoice.setDateTime(LocalDateTime.now());
        invoice.setStatus(InvoiceStatus.ISSUED);
        invoice.setItems(List.of(item));
        invoice = testEntityManager.persist(invoice);
        Assertions.assertEquals(1, invoiceRepository.findAll().size());
        Assertions.assertEquals(1, invoice.getId());
    }
}
