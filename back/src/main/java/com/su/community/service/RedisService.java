package com.su.community.service;

import com.su.community.dto.TopicDTO;

import java.util.List;

public interface RedisService {
    void setTokenInRedis(Long userId, String token);

    String getTokenInRedis(Long userId);

    Long getTokenExpireTime(Long userId);

    List<TopicDTO> getViewsInRedis();

}
