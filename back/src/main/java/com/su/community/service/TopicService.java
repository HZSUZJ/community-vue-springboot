package com.su.community.service;

import com.su.community.dto.TopicDTO;
import com.su.community.pojo.Topic;

import java.util.List;

public interface TopicService {
    void creatTopic(Topic topic);

    List<TopicDTO> getAllTopic();

    TopicDTO getTopicById(Long id);
}
