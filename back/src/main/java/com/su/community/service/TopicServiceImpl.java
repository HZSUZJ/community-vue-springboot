package com.su.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Topic;
import com.su.community.pojo.User;
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

    @Override
    public void creatTopic(Topic topic) {
        topicMapper.insert(topic);
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
        String boardName = boardMapper.selectById(topic.getBoard()).getName();
        topicDTO.setBoard(boardName);
        return topicDTO;
    }
}
