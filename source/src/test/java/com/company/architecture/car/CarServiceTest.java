package com.company.architecture.car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CarService.class})
class CarServiceTest {
    @Autowired
    CarService carService;

    @Test
    void shouldNotThrowExceptionWhenAdding() {
        Assertions.assertDoesNotThrow(() -> carService.add(new Car("Brand", "Model")));
    }

    @Test
    void shouldNotThrowExceptionWhenListen() {
        Assertions.assertDoesNotThrow(() -> carService.listen(new Car("Brand", "Model")));
    }
}
