package com.company.architecture;

import com.company.architecture.auth.Authority;
import com.company.architecture.auth.JwtService;
import com.company.architecture.shared.configurations.ExceptionConfiguration;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@TestConfiguration(proxyBeanMethods = false)
@Import(ExceptionConfiguration.class)
public class WebMvcTestConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(registry -> registry.anyRequest().permitAll())
            .build();
    }

    @Bean
    public JwtService jwtService() {
        final var jwtService = Mockito.mock(JwtService.class);
        when(jwtService.getSubject(anyString())).thenReturn(UUID.randomUUID().toString());
        when(jwtService.getAuthorities(anyString())).thenReturn(createAuthorityList(Authority.ADMINISTRATOR.toString()));
        when(jwtService.create(any())).thenReturn("");
        when(jwtService.verify(anyString())).thenReturn(true);
        return jwtService;
    }
}
