package com.company.architecture.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {
    private final AuthFilter authFilter;

    @Bean
    AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity security) throws Exception {
        return security
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(registry -> registry
                .requestMatchers("/", "/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/auth").permitAll()
                .requestMatchers(HttpMethod.DELETE).hasAuthority(Authority.ADMINISTRATOR.name())
                .anyRequest().authenticated()
            )
            .build();
    }
}
