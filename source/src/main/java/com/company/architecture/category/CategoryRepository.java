package com.company.architecture.category;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    public List<Category> list() {
        return List.of(new Category("Category"));
    }
}
