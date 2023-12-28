package architecture.group;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    public Map<Object, Map<String, Object>> groupPropertiesBy(Object object, String contains) {
        final var result = new HashMap<Object, Map<String, Object>>();

        Arrays.stream(object.getClass().getDeclaredFields()).filter(field -> field.getName().contains(contains)).forEach(field -> {
            final var wrapper = new BeanWrapperImpl(object);
            final var name = field.getName().replace(contains, "");
            result.computeIfAbsent(wrapper.getPropertyValue(field.getName()), key -> new HashMap<>()).put(name, wrapper.getPropertyValue(name));
        });

        return result;
    }

    public void setProperties(Object object, Map<String, Object> map) {
        final var wrapper = new BeanWrapperImpl(object);
        map.forEach((property, value) -> {
            if (Objects.nonNull(value)) {
                wrapper.setPropertyValue(property, value);
            }
        });
    }
}
