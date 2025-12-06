package io.yigitucun.eventflow.auth.service.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String,Object> template;
    private static final String BASE_PREFIX = "eventflow:auth:";

    public RedisService(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public String generateKey(String entity,String id){
        return BASE_PREFIX + entity + ":" + id;
    }
    public void saveRefreshToken(String userId, String token, long ttl) {
        String key = generateKey("refresh_token", userId);
        set(key, token, ttl);
    }

    public Object getRefreshToken(String userId) {
        String key = generateKey("refresh_token", userId);
        return get(key);
    }
    public void deleteRefreshToken(String userId) {
        String key = generateKey("refresh_token", userId);
        delete(key);
    }

    public void set(String key, String value, Long tll){
        template.opsForValue().set(key,value,tll, TimeUnit.SECONDS);
    }

    public Object get(String key){
        return template.opsForValue().get(key);
    }

    public void delete(String key){
        template.delete(key);
    }

}
