package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.TopicStatisticMapper;
import com.su.community.pojo.TopicStatistic;
import com.su.community.service.RedisService;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicStatisticServiceImpl implements TopicStatisticService {

    @Autowired
    private TopicStatisticMapper topicStatisticMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisService redisService;

    @Override
    public void creatTopicStatistic(TopicStatistic topicStatistic) {
        topicStatisticMapper.insert(topicStatistic);
    }

    @Override
    public TopicStatistic getTopicStatistic(Long topicId) {
        QueryWrapper<TopicStatistic> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", topicId);
        return topicStatisticMapper.selectOne(wrapper);
    }

    @Override
    public Integer getViews(Long topicId) {
        if (!redisTemplate.opsForHash().hasKey("views", String.valueOf(topicId))) {
            QueryWrapper<TopicStatistic> wrapper = new QueryWrapper<>();
            wrapper.eq("topic_id", topicId);
            TopicStatistic topicStatistic = topicStatisticMapper.selectOne(wrapper);
            redisTemplate.opsForHash().put("views", String.valueOf(topicId), topicStatistic.getViews());
            return topicStatistic.getViews();
        } else {
            return (Integer) redisTemplate.opsForHash().get("views", String.valueOf(topicId));
        }
    }

    @Override
    public void increaseViews(Long topicId) {
        if (!redisTemplate.opsForHash().hasKey("views", String.valueOf(topicId))) {
            QueryWrapper<TopicStatistic> wrapper = new QueryWrapper<>();
            wrapper.eq("topic_id", topicId);
            TopicStatistic topicStatistic = topicStatisticMapper.selectOne(wrapper);
            redisTemplate.opsForHash().put("views", String.valueOf(topicId), topicStatistic.getViews() + 1);
        } else {
            redisTemplate.opsForHash().increment("views", String.valueOf(topicId), 1);
        }
    }


    @Override
    public void transViewsFromRedis2DB() {
        List<TopicDTO> topicDTOS = redisService.getViewsInRedis();
        for (TopicDTO topicDTO : topicDTOS) {
            updateViews(topicDTO.getId(), topicDTO.getViews());
        }
    }

    @Override
    public void updateViews(Long topicId, Integer views) {
        UpdateWrapper<TopicStatistic> wrapper = new UpdateWrapper<>();
        wrapper.eq("topic_id", topicId).set("views", views);
        topicStatisticMapper.update(null, wrapper);
    }
}
