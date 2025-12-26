package io.yigitucun.eventflow.auth.service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.yigitucun.eventflow.auth.service.config.AppConfig;
import io.yigitucun.eventflow.auth.service.dto.client.responses.UserAuth;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    private final AppConfig appConfig;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtService(AppConfig appConfig) {
        this.appConfig = appConfig;
        byte[] keyBytes = Base64.getDecoder().decode(appConfig.getSecretKey());
        this.algorithm=Algorithm.HMAC256(keyBytes);
        this.verifier= JWT.require(algorithm).build();
    }

    public String generateAccessToken(UserAuth userAuth){
        return JWT.create()
                .withIssuer("Event-Flow")
                .withSubject(userAuth.getUsername())
                .withClaim("id",String.valueOf(userAuth.getId()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + appConfig.getAccessTokenExpiration()))
                .sign(algorithm);
    }

    public String generateRefreshToken(String username){
        return JWT.create()
                .withIssuer("Event-Flow")
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + appConfig.getRefreshTokenExpiration()))
                .sign(algorithm);
    }

    public String extractUsername(String token){
        return verifier.verify(token).getSubject();
    }

    public String extractUserId(String token){
        return verifier.verify(token).getClaim("id").toString();
    }


}

