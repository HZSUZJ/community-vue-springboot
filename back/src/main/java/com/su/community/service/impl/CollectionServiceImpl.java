package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.CollectionMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.pojo.Collection;
import com.su.community.pojo.Topic;
import com.su.community.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;


    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insert(collection);
    }

    @Override
    public void deleteCollection(Collection collection) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", collection.getUserId()).eq("topic_id", collection.getTopicId());
        collectionMapper.delete(wrapper);
    }

    @Override
    public List<TopicDTO> getCollectionsByUserId(Long uid) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid).orderByDesc("gmt_create");
        List<Collection> collections = collectionMapper.selectList(wrapper);
        List<Long> topicIds = new ArrayList<>();
        for (Collection collection : collections) {
            topicIds.add(collection.getTopicId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        List<Topic> topics = new ArrayList<>();
        if (topicIds.size() != 0) {
            topicQueryWrapper.in("id", topicIds);
            topics = topicMapper.selectList(topicQueryWrapper);
        }
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }
}
