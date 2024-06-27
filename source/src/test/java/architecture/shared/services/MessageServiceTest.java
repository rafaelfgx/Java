package architecture.shared.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { MessageService.class })
class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @Test
    void testGet() {
        Assertions.assertEquals("Test", messageService.get("Test"));
    }
}
