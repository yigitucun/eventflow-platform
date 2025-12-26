package io.yigitucun.eventflow.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtils {
    private final JWTVerifier verifier;

    public JwtUtils(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        Algorithm algorithm = Algorithm.HMAC256(keyBytes);
        this.verifier = JWT.require(algorithm).build();
    }

    public void validateToken(String token) {
        String cleanToken = resolveToken(token);
        verifier.verify(cleanToken);
    }

    public String extractUserId(String token) {
        String cleanToken = resolveToken(token);
        return verifier.verify(cleanToken).getClaim("id").asString();
    }

    private String resolveToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
