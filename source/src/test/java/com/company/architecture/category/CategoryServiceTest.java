package com.company.architecture.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CategoryService.class, CategoryCacheService.class, CategoryRepository.class})
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    void shouldNotThrowExceptionWhenListingCategories() {
        Assertions.assertDoesNotThrow(() -> categoryService.list());
    }
}
