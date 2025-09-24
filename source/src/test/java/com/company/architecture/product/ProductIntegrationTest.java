package com.company.architecture.product;

import com.company.architecture.SpringBootTestConfiguration;
import com.company.architecture.product.dtos.AddProductDto;
import com.company.architecture.product.dtos.UpdateProductDto;
import com.company.architecture.shared.Data;
import com.company.architecture.shared.services.MapperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Import(SpringBootTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MapperService mapperService;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @BeforeEach
    void beforeEach() {
        mongoTemplate.getDb().drop();
    }

    @ParameterizedTest
    @ValueSource(strings = {"?description=inexistent", "/" + Data.ID_INEXISTENT})
    void shouldReturnNotFoundWhenGettingProducts(String uri) throws Exception {
        mockMvc.perform(get("/products" + uri)).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {"page=0", "size=1", "sort=id", "sort=description", "direction=ASC", "direction=DESC", "description=Product"})
    void shouldReturnOkWhenGettingProducts(String uri) throws Exception {
        mongoTemplate.save(Data.PRODUCT);

        mockMvc.perform(get("/products?" + uri)).andExpectAll(
            status().isOk(),
            jsonPath("$.content").isArray(),
            jsonPath("$.content.length()").value(1),
            jsonPath("$.content[0].id").value(Data.PRODUCT.getId().toString()),
            jsonPath("$.content[0].description").value(Data.PRODUCT.getDescription()),
            jsonPath("$.content[0].price").value(Data.PRODUCT.getPrice()),
            jsonPath("$.page.number").value(0),
            jsonPath("$.page.totalElements").value(1),
            jsonPath("$.page.totalPages").value(1)
        );
    }

    @Test
    void shouldReturnOkWhenGettingProductById() throws Exception {
        mongoTemplate.save(Data.PRODUCT);

        mockMvc.perform(get("/products/" + Data.PRODUCT.getId())).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.PRODUCT.getId().toString()),
            jsonPath("$.description").value(Data.PRODUCT.getDescription()),
            jsonPath("$.price").value(Data.PRODUCT.getPrice())
        );
    }

    @Test
    void shouldReturnCreatedWhenCreatingProduct() throws Exception {
        mockMvc
            .perform(post("/products").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.PRODUCT, AddProductDto.class))))
            .andExpectAll(status().isCreated());
    }

    @Test
    void shouldReturnOkWhenUpdatingProduct() throws Exception {
        mongoTemplate.save(Data.PRODUCT);

        mockMvc
            .perform(put("/products/" + Data.PRODUCT.getId()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(mapperService.map(Data.PRODUCT_UPDATE, UpdateProductDto.class))))
            .andExpectAll(status().isOk());

        final var product = mongoTemplate.findById(Data.PRODUCT.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getPrice(), product.getPrice());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingPrice() throws Exception {
        mockMvc
            .perform(patch("/products/%s/price/100".formatted(Data.ID_INEXISTENT)))
            .andExpectAll(status().isNotFound());
    }

    @Test
    void shouldReturnOkWhenUpdatingPrice() throws Exception {
        mongoTemplate.save(Data.PRODUCT);

        mockMvc
            .perform(patch("/products/%s/price/%s".formatted(Data.PRODUCT.getId(), Data.PRODUCT_UPDATE.getPrice())))
            .andExpectAll(status().isOk());

        final var product = mongoTemplate.findById(Data.PRODUCT.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.PRODUCT.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.PRODUCT_UPDATE.getPrice(), product.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {Data.ID, Data.ID_INEXISTENT})
    void shouldReturnOkWhenDeletingProduct(String id) throws Exception {
        mongoTemplate.save(Data.PRODUCT);

        mockMvc
            .perform(delete("/products/" + id))
            .andExpectAll(status().isOk());

        Assertions.assertNull(mongoTemplate.findById(id, Product.class));
    }
}
