package com.su.community.service.impl;

import com.su.community.dto.TopicDTO;
import com.su.community.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private final Long DURATION = 1 * 24 * 60 * 60 * 1000L;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setTokenInRedis(Long userId, String token) {
        redisTemplate.opsForValue().set("token:" + userId, token, DURATION, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getTokenInRedis(Long userId) {
        return (String) redisTemplate.opsForValue().get("token:" + userId);
    }

    @Override
    public Long getTokenExpireTime(Long userId) {
        return redisTemplate.opsForValue().getOperations().getExpire("token:" + userId);
    }

    @Override
    public List<TopicDTO> getViewsInRedis() {
        List<TopicDTO> topics = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("views", ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            Long topicId = Long.parseLong(key);
            Integer views = (Integer) entry.getValue();
            TopicDTO topic = new TopicDTO();
            topic.setId(topicId);
            topic.setViews(views);
            topics.add(topic);
            redisTemplate.opsForHash().delete("views", key);
        }
        cursor.close();
        return topics;
    }
}
