package com.company.architecture.auth;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final @Nonnull HttpServletResponse response, final @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final var jwt = StringUtils.removeStart(StringUtils.defaultString(request.getHeader(HttpHeaders.AUTHORIZATION)), "Bearer").trim();

        if (jwtService.verify(jwt)) {
            SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(jwtService.getSubject(jwt), null, jwtService.getAuthorities(jwt)));
        }

        filterChain.doFilter(request, response);
    }
}
