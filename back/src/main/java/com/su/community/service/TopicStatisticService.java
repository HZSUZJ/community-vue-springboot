package com.su.community.service;

import com.su.community.pojo.TopicStatistic;

public interface TopicStatisticService {
    void creatTopicStatistic(TopicStatistic topicStatistic);

    TopicStatistic getTopicStatistic(Long topicId);

    void increaseViews(Long topicId);
}
