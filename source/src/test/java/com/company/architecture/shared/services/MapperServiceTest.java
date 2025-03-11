package com.company.architecture.shared.services;

import com.company.architecture.shared.Color;
import com.company.architecture.shared.Dto;
import com.company.architecture.shared.Entity;
import com.company.architecture.shared.configurations.JacksonConfiguration;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = {JacksonConfiguration.class, MapperService.class})
class MapperServiceTest {
    @Autowired
    MapperService mapperService;

    @Test
    void shouldReturnNullWhenMappingNullSourceToDto() {
        Assertions.assertNull(mapperService.map(null, Dto.class));
    }

    @Test
    void shouldReturnEqualObjectWhenMappingNonNullSourceToDto() {
        final var entity = getEntity();
        final var dto = mapperService.map(entity, Dto.class);
        assertEquals(entity, dto);
    }

    @Test
    void shouldReturnEmptyListWhenMappingNullSourceToList() {
        Assertions.assertEquals(Collections.emptyList(), mapperService.mapList(null, Dto.class));
    }

    @Test
    void shouldReturnEqualListWhenMappingNonNullSourceToList() {
        final var entity = getEntity();
        final var dtos = mapperService.mapList(List.of(entity), Dto.class);
        assertEquals(entity, dtos.getFirst());
    }

    @Test
    void shouldReturnNullWhenMappingNullSourceToPage() {
        Assertions.assertNull(mapperService.mapPage(null, Dto.class));
    }

    @Test
    void shouldReturnEqualPageWhenMappingNonNullSourceToPage() {
        final var expected = new PageImpl<>(List.of(getEntity()), PageRequest.of(0, 10), 1);
        final var actual = mapperService.mapPage(expected, Dto.class);

        Assertions.assertEquals(1, expected.getContent().size());
        Assertions.assertEquals(expected.getContent().getFirst().getUuid(), actual.getContent().getFirst().uuid());
        Assertions.assertEquals(expected.getContent().getFirst().getString(), actual.getContent().getFirst().string());
        Assertions.assertEquals(expected.getContent().getFirst().getBigDecimal(), actual.getContent().getFirst().bigDecimal());
        Assertions.assertEquals(expected.getContent().getFirst().getDate(), actual.getContent().getFirst().date());
        Assertions.assertEquals(expected.getContent().getFirst().getColor(), actual.getContent().getFirst().color());
        Assertions.assertEquals(expected.getTotalElements(), actual.getTotalElements());
        Assertions.assertEquals(expected.getNumber(), actual.getNumber());
    }

    @Test
    void shouldReturnNullWhenConvertingNullSourceToJson() {
        Assertions.assertNull(mapperService.toJson(null));
    }

    @Test
    void shouldReturnNotNullWhenConvertingNonNullSourceToJson() {
        final var expected = getEntity();
        final var actual = mapperService.toJson(expected);
        Assertions.assertEquals(expected.getUuid().toString(), JsonPath.read(actual, "$.uuid").toString());
        Assertions.assertEquals(expected.getString(), JsonPath.read(actual, "$.string").toString());
        Assertions.assertEquals(expected.getBigDecimal().toString(), JsonPath.read(actual, "$.bigDecimal").toString());
        Assertions.assertEquals(expected.getDate().toString(), JsonPath.read(actual, "$.date").toString());
        Assertions.assertEquals(expected.getColor().toString(), JsonPath.read(actual, "$.color").toString());
    }

    @Test
    void shouldReturnNullWhenConvertingNullJsonToEntity() {
        Assertions.assertNull(mapperService.fromJson(null, Entity.class));
    }

    @Test
    void shouldReturnEqualEntityWhenConvertingNonNullJsonToEntity() {
        final var expected = getEntity();
        final var actual = mapperService.fromJson(mapperService.toJson(expected), Entity.class);

        Assertions.assertEquals(expected.getUuid(), actual.getUuid());
        Assertions.assertEquals(expected.getString(), actual.getString());
        Assertions.assertEquals(expected.getBigDecimal(), actual.getBigDecimal());
        Assertions.assertEquals(expected.getDate(), actual.getDate());
        Assertions.assertEquals(expected.getColor(), actual.getColor());
    }

    private static Entity getEntity() {
        return new Entity(UUID.randomUUID(), "Description", BigDecimal.valueOf(100), LocalDate.now(), Color.RED);
    }

    private static void assertEquals(Entity entity, Dto dto) {
        Assertions.assertEquals(entity.getUuid(), dto.uuid());
        Assertions.assertEquals(entity.getString(), dto.string());
        Assertions.assertEquals(entity.getBigDecimal(), dto.bigDecimal());
        Assertions.assertEquals(entity.getDate(), dto.date());
        Assertions.assertEquals(entity.getColor(), dto.color());
    }
}
