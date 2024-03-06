package architecture.user.dtos;

import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String email;
}
