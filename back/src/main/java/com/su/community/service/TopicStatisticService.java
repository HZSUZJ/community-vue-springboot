package com.su.community.service;

import com.su.community.pojo.TopicStatistic;

public interface TopicStatisticService {
    void creatTopicStatistic(TopicStatistic topicStatistic);

    TopicStatistic getTopicStatistic(Long topicId);

    Integer getViews(Long topicId);

    void increaseViews(Long topicId);

    void transViewsFromRedis2DB();

    void updateViews(Long topicId, Integer views);


}
