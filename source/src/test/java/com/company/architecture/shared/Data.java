package com.company.architecture.shared;

import com.company.architecture.auth.Authority;
import com.company.architecture.product.Product;
import com.company.architecture.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Data {
    public static final String ID = "AA8B9189-5220-4345-9230-6301765EA5A6";
    public static final String ID_INEXISTENT = "D095635D-249C-469C-BA1E-6D78F940177C";
    public static final String PASSWORD = "123456";
    public static final String PASSWORD_ENCODED = new BCryptPasswordEncoder().encode(PASSWORD);
    public static final List<Authority> AUTHORITIES = List.of(Authority.ADMINISTRATOR);
    public static final User USER = new User(UUID.fromString(ID), "User", "user@mail.com", "user", PASSWORD_ENCODED, AUTHORITIES);
    public static final User USER_UPDATE = new User(UUID.fromString(ID), "User Update", "user.update@mail.com", "user", PASSWORD_ENCODED, AUTHORITIES);
    public static final User USER_CONFLICT = new User(UUID.randomUUID(), "User Conflict", "user@mail.com", "user", PASSWORD_ENCODED, AUTHORITIES);
    public static final Product PRODUCT = new Product(UUID.fromString(ID), "Product", BigDecimal.valueOf(100L));
    public static final Product PRODUCT_UPDATE = new Product(UUID.fromString(ID), "Product Update", BigDecimal.valueOf(200L));
}
