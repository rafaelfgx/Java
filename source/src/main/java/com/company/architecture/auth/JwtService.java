package com.company.architecture.auth;

import com.company.architecture.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public String create(final User user) {
        final var authorities = user.getAuthorities().stream().map(Enum::name).toArray(String[]::new);
        final var claims = JwtClaimsSet.builder().subject(user.getId().toString()).claim("authorities", authorities).build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean verify(final String jwt) {
        try {
            decoder.decode(jwt);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public String getSubject(final String jwt) {
        return decoder.decode(jwt).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(final String jwt) {
        return AuthorityUtils.createAuthorityList(decoder.decode(jwt).getClaimAsStringList("authorities"));
    }
}
