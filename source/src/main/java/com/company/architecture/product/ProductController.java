package com.company.architecture.product;

import com.company.architecture.product.dtos.*;
import com.company.architecture.shared.swagger.DefaultApiResponses;
import com.company.architecture.shared.swagger.GetApiResponses;
import com.company.architecture.shared.swagger.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Tag(name = "Products")
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get")
    @GetApiResponses
    @GetMapping
    public Page<ProductDto> get(@ParameterObject @ModelAttribute @Valid final GetProductDto dto) {
        return productService.get(dto);
    }

    @Operation(summary = "Get")
    @GetApiResponses
    @GetMapping("{id}")
    public ProductDto get(@PathVariable final UUID id) {
        return productService.get(id);
    }

    @Operation(summary = "Add")
    @PostApiResponses
    @PostMapping
    public UUID add(@RequestBody @Valid final AddProductDto dto) {
        return productService.add(dto);
    }

    @Operation(summary = "Update")
    @DefaultApiResponses
    @PutMapping("{id}")
    public void update(@PathVariable final UUID id, @RequestBody @Valid final UpdateProductDto dto) {
        productService.update(dto.withId(id));
    }

    @Operation(summary = "Update Price")
    @DefaultApiResponses
    @PatchMapping("{id}/price/{price}")
    public void update(@PathVariable final UUID id, @PathVariable final BigDecimal price) {
        productService.update(new UpdatePriceProductDto(id, price));
    }

    @Operation(summary = "Delete")
    @DefaultApiResponses
    @DeleteMapping("{id}")
    public void delete(@PathVariable final UUID id) {
        productService.delete(id);
    }
}
