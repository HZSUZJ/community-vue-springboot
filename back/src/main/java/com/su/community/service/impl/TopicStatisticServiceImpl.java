package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.su.community.mapper.TopicStatisticMapper;
import com.su.community.pojo.TopicStatistic;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicStatisticServiceImpl implements TopicStatisticService {

    @Autowired
    private TopicStatisticMapper topicStatisticMapper;


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
    public void increaseViews(Long topicId) {
        UpdateWrapper<TopicStatistic> wrapper = new UpdateWrapper<>();
        wrapper.eq("topic_id", topicId).setSql("views=views+1");
        TopicStatistic topicStatistic = new TopicStatistic();
        topicStatistic.setTopicId(topicId);
        topicStatisticMapper.update(topicStatistic, wrapper);
    }
}
