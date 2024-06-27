package architecture.shared.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final ObjectMapper objectMapper;

    public <U> U map(Object source, Class<U> destination) {
        return objectMapper.convertValue(source, destination);
    }

    public <T, U> Page<U> map(Page<T> source, Class<U> destination) {
        return new PageImpl<>(source.stream().map(item -> map(item, destination)).toList(), source.getPageable(), source.getTotalElements());
    }

    public String json(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }
}
