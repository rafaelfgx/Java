package architecture.product;

import architecture.product.dtos.AddProductDto;
import architecture.product.dtos.GetProductDto;
import architecture.product.dtos.ProductDto;
import architecture.product.dtos.UpdatePriceProductDto;
import architecture.product.dtos.UpdateProductDto;
import architecture.shared.services.MapperService;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final MapperService mapperService;
    private final ProductRepository productRepository;

    public Page<ProductDto> get(final GetProductDto dto) {
        final var entities = productRepository.findAll(dto.getExample(Product.class), dto.getPageable());
        if (entities.isEmpty()) throw new NoSuchElementException();
        return mapperService.map(entities, ProductDto.class);
    }

    public ProductDto get(final UUID id) {
        return mapperService.map(productRepository.findById(id).orElseThrow(), ProductDto.class);
    }

    public UUID add(final AddProductDto dto) {
        final var entity = mapperService.map(dto, Product.class);
        entity.setId(UUID.randomUUID());
        return productRepository.insert(entity).getId();
    }

    public void update(final UpdateProductDto dto) {
        productRepository.save(mapperService.map(dto, Product.class));
    }

    public void update(final UpdatePriceProductDto dto) {
        final var entity = productRepository.findById(dto.getId()).orElseThrow();
        BeanUtils.copyProperties(dto, entity);
        productRepository.save(entity);
    }

    public void delete(final UUID id) {
        productRepository.deleteById(id);
    }
}
