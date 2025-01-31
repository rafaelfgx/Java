package com.company.architecture.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCacheService {
    private static final String KEY = "Category";
    private final CategoryRepository categoryRepository;

    @Cacheable(KEY)
    public List<Category> list() {
        log.info("Cache -> Loading: {}", KEY);
        return categoryRepository.list();
    }

    @CacheEvict(allEntries = true, cacheNames = {KEY})
    @Scheduled(fixedRateString = "1", timeUnit = TimeUnit.HOURS)
    public void cacheEvict() {
        log.info("Cache -> Cleaning: {}", KEY);
    }
}
