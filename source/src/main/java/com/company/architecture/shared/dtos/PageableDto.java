package com.company.architecture.shared.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;

@Data
public class PageableDto {
    @PositiveOrZero
    private int page = 0;

    @Positive
    private int size = 2_000_000_000;

    private String sort = "id";

    private Direction direction = Direction.ASC;

    public Pageable getPageable() {
        return PageRequest.of(page, size, Sort.by(direction, sort));
    }

    public <T> Example<T> getExample(final Class<T> clazz) {
        var instance = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, instance);
        return Example.of(instance, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
    }
}
