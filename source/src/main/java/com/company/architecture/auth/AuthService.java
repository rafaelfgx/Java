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
        return userRepository
            .findByUsername(dto.username())
            .filter(user -> passwordEncoder.matches(dto.password(), user.getPassword()))
            .map(jwtService::create)
            .orElseThrow(() -> new ApplicationException(HttpStatus.UNAUTHORIZED, messageService.get("auth.unauthorized")));
    }
}
