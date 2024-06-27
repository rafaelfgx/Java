package architecture.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryCacheService categoryCacheService;

    public List<Category> list() {
        return categoryCacheService.list();
    }
}
