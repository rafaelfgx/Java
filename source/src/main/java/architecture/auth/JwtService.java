package architecture.auth;

import architecture.user.User;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final Algorithm algorithm;

    public JwtService() throws NoSuchAlgorithmException {
        final var keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
    }

    public String create(final User user) {
        final var authorities = user.getAuthorities().stream().map(Enum::name).toArray(String[]::new);
        return JWT.create().withSubject(user.getId().toString()).withArrayClaim("authorities", authorities).sign(algorithm);
    }

    public boolean verify(final String jwt) {
        try {
            algorithm.verify(JWT.decode(jwt));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Generated
    public boolean verifyInProvider(final String jwt) {
        try {
            final var decoded = JWT.decode(jwt);
            final var provider = new JwkProviderBuilder("DOMAIN").build();
            final var jwk = provider.get(decoded.getKeyId());
            final var key = (RSAKey) jwk.getPublicKey();
            Algorithm.RSA256(key).verify(decoded);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public String getSubject(final String jwt) {
        return JWT.decode(jwt).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(final String jwt) {
        return AuthorityUtils.createAuthorityList(JWT.decode(jwt).getClaim("authorities").asList(String.class));
    }
}
