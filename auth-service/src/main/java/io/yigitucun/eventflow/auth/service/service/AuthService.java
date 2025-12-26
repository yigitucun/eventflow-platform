package io.yigitucun.eventflow.auth.service.service;

import io.yigitucun.eventflow.auth.service.client.UserClientService;
import io.yigitucun.eventflow.auth.service.dto.client.responses.UserAuth;
import io.yigitucun.eventflow.auth.service.dto.requests.AuthRequest;
import io.yigitucun.eventflow.auth.service.dto.requests.TokenRefreshRequest;
import io.yigitucun.eventflow.auth.service.dto.responses.TokenResponse;
import io.yigitucun.eventflow.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final RedisService redisService;
    private final UserClientService userClientService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(RedisService redisService, UserClientService userClientService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.redisService = redisService;
        this.userClientService = userClientService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenResponse refreshToken(TokenRefreshRequest request){
        String username;
        try{
           username = jwtService.extractUsername(request.getRefreshToken());
        } catch (Exception e) {
            throw new GlobalException("invalid token", HttpStatus.UNAUTHORIZED);
        }
        UserAuth userAuth = userClientService.findUserByUsername(username);

        Object storedToken = redisService.getRefreshToken(String.valueOf(userAuth.getId())).toString();

        if (storedToken==null ||!storedToken.equals(request.getRefreshToken())){
            throw new GlobalException("invalid token",HttpStatus.UNAUTHORIZED);
        }

        String accessToken = jwtService.generateAccessToken(userAuth);
        String refreshToken = jwtService.generateRefreshToken(username);
        redisService.saveRefreshToken(String.valueOf(userAuth.getId()),refreshToken,604800L);

        return new TokenResponse(accessToken,refreshToken);

    }

    public TokenResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        UserAuth auth = this.userClientService.findUserByUsername(request.getUsername());
        String accessToken = jwtService.generateAccessToken(auth);
        String refreshToken = jwtService.generateRefreshToken(auth.getUsername());
        redisService.saveRefreshToken(String.valueOf(auth.getId()),refreshToken,604800L);
        return new TokenResponse(accessToken,refreshToken);
    }

    public void logout(String token){
        String userId = jwtService.extractUserId(token);
        redisService.deleteRefreshToken(userId);
    }

}
