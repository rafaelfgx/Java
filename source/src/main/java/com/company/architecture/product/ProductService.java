package com.company.architecture.product;

import com.company.architecture.product.dtos.*;
import com.company.architecture.shared.services.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final MapperService mapperService;
    private final ProductRepository productRepository;

    public Page<ProductDto> get(final GetProductDto dto) {
        final var products = productRepository.findAll(dto.getExample(Product.class), dto.getPageable());
        if (products.isEmpty()) throw new NoSuchElementException();
        return mapperService.mapPage(products, ProductDto.class);
    }

    public ProductDto get(final UUID id) {
        return productRepository.findById(id).map(product -> mapperService.map(product, ProductDto.class)).orElseThrow();
    }

    public UUID add(final AddProductDto dto) {
        final var product = mapperService.map(dto, Product.class);
        product.setId(UUID.randomUUID());
        return productRepository.insert(product).getId();
    }

    public void update(final UpdateProductDto dto) {
        productRepository.save(mapperService.map(dto, Product.class));
    }

    public void update(final UpdatePriceProductDto dto) {
        final var product = productRepository.findById(dto.id()).orElseThrow();
        BeanUtils.copyProperties(dto, product);
        productRepository.save(product);
    }

    public void delete(final UUID id) {
        productRepository.deleteById(id);
    }
}
