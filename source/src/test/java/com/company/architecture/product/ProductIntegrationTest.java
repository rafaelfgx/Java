package com.company.architecture.product;

import com.company.architecture.Data;
import com.company.architecture.IntegrationTest;
import com.company.architecture.product.dtos.AddProductDto;
import com.company.architecture.product.dtos.UpdateProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductIntegrationTest extends IntegrationTest {
    @ParameterizedTest
    @ValueSource(strings = {"?description=inexistent", "/" + Data.ID_INEXISTENT})
    void testGetShouldReturnNotFound(String uri) throws Exception {
        perform(GET, "/products" + uri).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {"page=0", "size=1", "sort=id", "sort=description", "direction=ASC", "direction=DESC", "description=Product"})
    void testGetShouldReturnOk(String uri) throws Exception {
        mongoTemplate.save(Data.PRODUCT);
        perform(GET, "/products?" + uri).andExpectAll(
            status().isOk(),
            jsonPath("$.content").isArray(),
            jsonPath("$.content.length()").value(1),
            jsonPath("$.empty").value(false),
            jsonPath("$.first").value(true),
            jsonPath("$.last").value(true),
            jsonPath("$.numberOfElements").value(1),
            jsonPath("$.totalElements").value(1),
            jsonPath("$.number").value(0),
            jsonPath("$.totalPages").value(1),
            jsonPath("$.sort.empty").value(false),
            jsonPath("$.sort.sorted").value(true),
            jsonPath("$.sort.unsorted").value(false),
            jsonPath("$.content[0].id").value(Data.PRODUCT.getId().toString()),
            jsonPath("$.content[0].description").value(Data.PRODUCT.getDescription()),
            jsonPath("$.content[0].price").value(Data.PRODUCT.getPrice())
        );
    }

    @Test
    void testGetShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.PRODUCT);
        perform(GET, "/products/" + Data.PRODUCT.getId(), null).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.PRODUCT.getId().toString()),
            jsonPath("$.description").value(Data.PRODUCT.getDescription()),
            jsonPath("$.price").value(Data.PRODUCT.getPrice())
        );
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        perform(POST, "/products", mapperService.map(Data.PRODUCT, AddProductDto.class)).andExpectAll(status().isCreated());
    }

    @Test
    void testPutShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.PRODUCT);
        perform(PUT, "/products/" + Data.PRODUCT.getId(), mapperService.map(Data.PRODUCT_UPDATE, UpdateProductDto.class)).andExpectAll(status().isOk());
        final var product = mongoTemplate.findById(Data.PRODUCT.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getPrice(), product.getPrice());
    }

    @Test
    void testPatchShouldReturnNotFound() throws Exception {
        perform(PATCH, "/products/%s/price/100".formatted(Data.ID_INEXISTENT)).andExpectAll(status().isNotFound());
    }

    @Test
    void testPatchShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.PRODUCT);
        perform(PATCH, "/products/%s/price/%s".formatted(Data.PRODUCT.getId(), Data.PRODUCT_UPDATE.getPrice()), null).andExpectAll(status().isOk());
        final var product = mongoTemplate.findById(Data.PRODUCT.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.PRODUCT.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getPrice(), product.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {Data.ID, Data.ID_INEXISTENT})
    void testDeleteShouldReturnOk(String id) throws Exception {
        mongoTemplate.save(Data.PRODUCT);
        perform(DELETE, "/products/" + id).andExpectAll(status().isOk());
        Assertions.assertNull(mongoTemplate.findById(id, Product.class));
    }
}
