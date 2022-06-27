package com.su.community.service;

import com.su.community.dto.TopicDTO;
import com.su.community.pojo.Topic;

import java.util.List;

public interface TopicService {
    Long creatTopic(Topic topic);

    List<TopicDTO> getAllTopic();

    TopicDTO getTopicById(Long topicId, Long uid);

    List<TopicDTO> getTopicsByUserId(Long uid);

    List<TopicDTO> getTopicsByBoardIdAndPage(Integer boardId, Integer current);

    Long getTotalCountByBoardId(Integer boardId);

}
