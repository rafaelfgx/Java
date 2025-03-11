package com.company.architecture.shared.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class MapperService {
    private final ObjectMapper objectMapper;

    public <T> T map(Object source, Class<T> target) {
        return objectMapper.convertValue(source, target);
    }

    public <T> List<T> mapList(@Nullable List<?> source, Class<T> target) {
        return isNull(source) ? Collections.emptyList() : source.stream().map(entity -> map(entity, target)).toList();
    }

    public <T, U> Page<U> mapPage(Page<T> source, Class<U> target) {
        return isNull(source) ? null : new PageImpl<>(source.stream().map(item -> map(item, target)).toList(), source.getPageable(), source.getTotalElements());
    }

    @SneakyThrows
    public String toJson(Object source) {
        return isNull(source) ? null : objectMapper.writeValueAsString(source);
    }

    @SneakyThrows
    public <T> T fromJson(String source, Class<T> target) {
        return isNull(source) ? null : objectMapper.readValue(source, target);
    }
}
