package architecture.product;

import architecture.IntegrationTest;
import architecture.Data;
import architecture.product.dtos.AddProductDto;
import architecture.product.dtos.UpdateProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductTest extends IntegrationTest {
    @ParameterizedTest
    @ValueSource(strings = { "?page=-1", "?page=9999999999", "?page=X", "?size=0", "?size=9999999999", "?size=X", "?direction=X", "/1", "/X" })
    void testGetShouldReturnBadRequest(String uri) throws Exception {
        get("/products" + uri).andExpectAll(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = { "?description=inexistent", "/" + Data.idInexistent })
    void testGetShouldReturnNotFound(String uri) throws Exception {
        get("/products" + uri).andExpectAll(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = { "page=0", "size=1", "sort=id", "sort=description", "direction=ASC", "direction=DESC", "description=Product" })
    void testGetShouldReturnOk(String uri) throws Exception {
        mongoTemplate.save(Data.product);
        get("/products?" + uri).andExpectAll(
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
            jsonPath("$.content[0].id").value(Data.product.getId().toString()),
            jsonPath("$.content[0].description").value(Data.product.getDescription()),
            jsonPath("$.content[0].price").value(Data.product.getPrice())
        );
    }

    @Test
    void testGetShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.product);
        get("/products/" + Data.product.getId()).andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(Data.product.getId().toString()),
            jsonPath("$.description").value(Data.product.getDescription()),
            jsonPath("$.price").value(Data.product.getPrice())
        );
    }

    @Test
    void testPostShouldReturnCreated() throws Exception {
        post("/products", mapperService.map(Data.product, AddProductDto.class)).andExpectAll(status().isCreated());
    }

    @Test
    void testPutShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.product);
        put("/products/" + Data.product.getId(), mapperService.map(Data.productUpdate, UpdateProductDto.class)).andExpectAll(status().isOk());
        final var product = mongoTemplate.findById(Data.product.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.productUpdate.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.productUpdate.getPrice(), product.getPrice());
    }

    @Test
    void testPatchShouldReturnNotFound() throws Exception {
        patch("/products/%s/price/100".formatted(Data.idInexistent)).andExpectAll(status().isNotFound());
    }

    @Test
    void testPatchShouldReturnOk() throws Exception {
        mongoTemplate.save(Data.product);
        patch("/products/%s/price/%s".formatted(Data.product.getId(), Data.productUpdate.getPrice())).andExpectAll(status().isOk());
        final var product = mongoTemplate.findById(Data.product.getId(), Product.class);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(Data.product.getDescription(), product.getDescription());
        Assertions.assertEquals(Data.productUpdate.getPrice(), product.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = { Data.id, Data.idInexistent })
    void testDeleteShouldReturnOk(String id) throws Exception {
        mongoTemplate.save(Data.product);
        delete("/products/" + id).andExpectAll(status().isOk());
        Assertions.assertNull(mongoTemplate.findById(id, Product.class));
    }
}
