package com.su.community.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final Long DURATION = 1 * 24 * 60 * 60 * 1000L;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, DURATION, TimeUnit.MILLISECONDS);
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get("key");
    }

    public boolean delete(String key) {
        boolean result = redisTemplate.delete(key);
        return result;
    }

    public Long getExpireTime(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

}
