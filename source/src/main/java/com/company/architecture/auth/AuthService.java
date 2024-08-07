package com.company.architecture.auth;

import com.company.architecture.shared.exception.ApplicationException;
import com.company.architecture.shared.services.MessageService;
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
    private final MessageService messageService;
    private final UserRepository userRepository;

    public String auth(final AuthDto dto) {
        final var user = userRepository.findByUsername(dto.username());

        if (user.isEmpty() || !passwordEncoder.matches(dto.password(), user.get().getPassword())) {
            throw new ApplicationException(HttpStatus.UNAUTHORIZED, messageService.get("auth.unauthorized"));
        }

        return jwtService.create(user.get());
    }
}
