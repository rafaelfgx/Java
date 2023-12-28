package architecture.product;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
