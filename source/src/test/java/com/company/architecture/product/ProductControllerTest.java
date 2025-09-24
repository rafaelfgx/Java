package com.company.architecture.product;

import com.company.architecture.WebMvcTestConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Import(WebMvcTestConfiguration.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @ParameterizedTest
    @MethodSource
    void testGetShouldReturnBadRequest(String uri, String message) throws Exception {
        mockMvc.perform(get("/products" + uri)).andExpectAll(status().isBadRequest(), content().string(message));
    }

    static Stream<Arguments> testGetShouldReturnBadRequest() {
        return Stream.of(
            of("?page=-1", "[page: must be greater than or equal to 0]"),
            of("?page=9999999999", "[page: must be valid]"),
            of("?page=X", "[page: must be valid]"),
            of("?size=0", "[size: must be greater than 0]"),
            of("?size=9999999999", "[size: must be valid]"),
            of("?size=X", "[size: must be valid]"),
            of("?direction=X", "[direction: must be valid]"),
            of("/1", "[id: must be valid]"),
            of("/X", "[id: must be valid]")
        );
    }
}
