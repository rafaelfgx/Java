package com.company.architecture.shared.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@Data
public class PageableDto {
    @PositiveOrZero
    private int page;

    @Positive
    private int size = 2_000_000_000;

    private String sort = "id";

    private Direction direction = Direction.ASC;

    private Pageable pageable = PageRequest.of(page, size, direction, sort);

    public <T> Example<T> getExample(final Class<T> clazz) {
        var instance = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, instance);
        return Example.of(instance, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
    }
}
