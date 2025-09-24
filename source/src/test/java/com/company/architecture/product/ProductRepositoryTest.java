package com.company.architecture.product;

import com.company.architecture.MongoTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@DataMongoTest
@Import(MongoTestConfiguration.class)
class ProductRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        mongoTemplate.getDb().drop();
    }

    @Test
    void shouldPersistProduct() {
        mongoTemplate.save(new Product(UUID.randomUUID(), "Ball", BigDecimal.valueOf(100)));
        Assertions.assertFalse(productRepository.findAll().isEmpty());
    }
}
