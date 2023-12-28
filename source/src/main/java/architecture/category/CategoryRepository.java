package architecture.category;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
    public List<Category> list() {
        return List.of(new Category("Category"));
    }
}
