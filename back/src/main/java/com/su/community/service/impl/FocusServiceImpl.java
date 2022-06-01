package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.*;
import com.su.community.pojo.*;
import com.su.community.service.FocusService;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private TopicStatisticService topicStatisticService;

    @Override
    public List<TopicDTO> focusBoard(Long uid) {
        return null;
    }

    @Override
    public List<TopicDTO> focusUser(Long uid) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        List<Follow> follows = followMapper.selectList(wrapper);
        List<Long> userIds = new ArrayList<>();
        for (Follow follow : follows) {
            userIds.add(follow.getFolloweeId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        topicQueryWrapper.in("creator", userIds).orderByDesc("gmt_modified");
        List<Topic> topics = topicMapper.selectList(topicQueryWrapper);
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
    public List<TopicDTO> focusFavorite(Long uid) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        List<Collection> collections = collectionMapper.selectList(wrapper);
        List<Long> topicIds = new ArrayList<>();
        for (Collection collection : collections) {
            topicIds.add(collection.getTopicId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        topicQueryWrapper.in("id", topicIds).orderByDesc("gmt_modified");
        List<Topic> topics = topicMapper.selectList(topicQueryWrapper);
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
}
