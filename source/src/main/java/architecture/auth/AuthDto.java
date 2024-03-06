package architecture.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(@NotBlank String username, @NotBlank String password) {
}
