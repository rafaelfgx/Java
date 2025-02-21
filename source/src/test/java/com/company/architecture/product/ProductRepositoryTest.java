package com.company.architecture.product;

import com.company.architecture.MongoTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

class ProductRepositoryTest extends MongoTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldPersistProduct() {
        mongoTemplate.save(new Product(UUID.randomUUID(), "Ball", BigDecimal.valueOf(100)));
        Assertions.assertFalse(productRepository.findAll().isEmpty());
    }
}
