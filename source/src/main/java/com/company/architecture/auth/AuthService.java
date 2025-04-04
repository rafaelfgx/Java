package com.company.architecture.auth;

import com.company.architecture.shared.exception.ApplicationException;
import com.company.architecture.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String auth(final Auth auth) {
        return userRepository
            .findByUsername(auth.username())
            .filter(user -> passwordEncoder.matches(auth.password(), user.getPassword()))
            .map(jwtService::create)
            .orElseThrow(() -> new ApplicationException(HttpStatus.UNAUTHORIZED, "auth.unauthorized"));
    }
}
