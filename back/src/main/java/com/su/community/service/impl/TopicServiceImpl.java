package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Topic;
import com.su.community.pojo.TopicStatistic;
import com.su.community.pojo.User;
import com.su.community.service.TopicService;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private TopicStatisticService topicStatisticService;

    @Override
    public void creatTopic(Topic topic) {
        topicMapper.insert(topic);
        TopicStatistic topicStatistic = new TopicStatistic();
        topicStatistic.setTopicId(topic.getId());
        topicStatisticService.creatTopicStatistic(topicStatistic);
    }

    @Override
    public List<TopicDTO> getAllTopic() {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<Topic> topics = topicMapper.selectList(wrapper);
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            User user = userMapper.selectById(topic.getCreator());
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topicDTO.getId());
            topicDTO.setViews(topicStatistic.getViews());
            topicDTO.setLikeCount(topicStatistic.getLikeCount());
            topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    @Override
    public TopicDTO getTopicById(Long id) {
        Topic topic = topicMapper.selectById(id);
        User user = userMapper.selectById(topic.getCreator());
        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic, topicDTO);
        topicDTO.setUser(user);
        TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(id);
        topicDTO.setViews(topicStatistic.getViews());
        topicDTO.setLikeCount(topicStatistic.getLikeCount());
        topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
        topicDTO.setCommentCount(topicStatistic.getCommentCount());
        String boardName = boardMapper.selectById(topic.getBoard()).getName();
        topicDTO.setBoard(boardName);
        return topicDTO;
    }
}
