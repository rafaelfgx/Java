package com.company.architecture.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryCacheService categoryCacheService;

    public List<Category> list() {
        return categoryCacheService.list();
    }
}
