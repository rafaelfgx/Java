package architecture.location;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class State extends Location {
    private List<City> cities = new ArrayList<>();
}
