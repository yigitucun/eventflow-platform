package io.yigitucun.eventflow.gateway.service.filter;

import io.yigitucun.eventflow.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationGatewayFilterFactory  extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtUtils jwtUtils;

    public AuthenticationGatewayFilterFactory (JwtUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader==null || !authHeader.startsWith("Bearer ")){
                return chain.filter(exchange);
            }
            try {
                String token = authHeader.substring(7);
                jwtUtils.validateToken(token);
                String userId = jwtUtils.extractUserId(token);
                ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                        .header("X-User-Id",userId)
                        .build();
                return chain.filter(exchange.mutate().request(serverHttpRequest).build());
            }catch (Exception e){
                return onError(exchange,"Invalid token",HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    public static class Config {

    }


}
