package architecture.location;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends Location {
    private List<State> states = new ArrayList<>();
}
