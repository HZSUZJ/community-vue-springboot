package com.su.community.service;

import com.su.community.dto.TopicDTO;

import java.util.List;

public interface TopicService {
    Long creatTopic(String title, String content, Boolean notify, Integer board);

    List<TopicDTO> getAllTopic();

    TopicDTO getTopicById(Long topicId);

    List<TopicDTO> getTopicsByUserId(Long uid);

    List<TopicDTO> getMyTopics();
    
    List<TopicDTO> getTopicsByBoardIdAndPage(Integer boardId, Integer current);

    Long getTotalCountByBoardId(Integer boardId);

}
