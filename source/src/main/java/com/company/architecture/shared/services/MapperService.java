package com.company.architecture.shared.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        return isNull(source) ? Page.empty() : source.map(element -> map(element, target));
    }

    public String toJson(Object source) {
        try {
            return isNull(source) ? null : objectMapper.writeValueAsString(source);
        } catch (Exception exception) {
            return null;
        }
    }

    public <T> T fromJson(String source, Class<T> target) {
        try {
            return isNull(source) ? null : objectMapper.readValue(source, target);
        } catch (Exception exception) {
            return null;
        }
    }
}
