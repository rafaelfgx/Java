package architecture;

import architecture.auth.Authority;
import architecture.product.Product;
import architecture.user.User;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Data {
    public static final String id = "AA8B9189-5220-4345-9230-6301765EA5A6";
    public static final String idInexistent = "D095635D-249C-469C-BA1E-6D78F940177C";
    public static final String password = "123456";
    public static final String passwordEncoded = new BCryptPasswordEncoder().encode(password);
    public static final List<Authority> authorities = List.of(Authority.ADMINISTRATOR);
    public static final User user = new User(UUID.fromString(id), "User", "user@mail.com", "user", passwordEncoded, authorities);
    public static final User userUpdate = new User(UUID.fromString(id), "User Update", "user.update@mail.com", "user", passwordEncoded, authorities);
    public static final User userConflict = new User(UUID.randomUUID(), "User Conflict", "user@mail.com", "user", passwordEncoded, authorities);
    public static final Product product = new Product(UUID.fromString(id), "Product", BigDecimal.valueOf(100L));
    public static final Product productUpdate = new Product(UUID.fromString(id), "Product Update", BigDecimal.valueOf(200L));
}
