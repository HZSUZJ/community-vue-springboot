package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.*;
import com.su.community.pojo.*;
import com.su.community.service.FollowService;
import com.su.community.service.TopicService;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    private CommentMapper commentMapper;
    @Autowired
    private TopicStatisticService topicStatisticService;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private FollowService followService;

    @Override
    public Long creatTopic(Topic topic) {
        topicMapper.insert(topic);
        TopicStatistic topicStatistic = new TopicStatistic();
        topicStatistic.setTopicId(topic.getId());
        topicStatisticService.creatTopicStatistic(topicStatistic);
        UpdateWrapper<Board> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", topic.getBoard())
                .setSql("post_count=post_count+1")
                .setSql("today_count=today_count+1");
        boardMapper.update(null, wrapper);
        return topic.getId();
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
    public TopicDTO getTopicById(Long topicId, Long uid) {
        Topic topic = topicMapper.selectById(topicId);
        User user = userMapper.selectById(topic.getCreator());

        List<Follow> follows = followService.getAllFollowee(uid);
        List<Long> followeeIds = new ArrayList<>();
        for (Follow follow : follows) {
            followeeIds.add(follow.getFolloweeId());
        }

        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic, topicDTO);
        topicDTO.setUser(user);
        TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topicId);
        topicDTO.setViews(topicStatistic.getViews());
        topicDTO.setLikeCount(topicStatistic.getLikeCount());
        topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
        topicDTO.setCommentCount(topicStatistic.getCommentCount());
        //判断是不是自己的帖子
        topicDTO.setIsMine(topic.getCreator().equals(uid));
        //判断贴主是不是自己关注的人
        topicDTO.setIsFollowee(followeeIds.contains(topic.getCreator()));
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", topicId).eq("user_id", uid);
        Collection collection = collectionMapper.selectOne(wrapper);
        topicDTO.setIsCollection(collection != null);

        String boardName = boardMapper.selectById(topic.getBoard()).getName();
        topicDTO.setBoard(boardName);
        return topicDTO;
    }

    @Override
    public List<TopicDTO> getTopicsByUserId(Long uid) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("creator", uid).orderByDesc("gmt_create");
        List<Topic> topics = topicMapper.selectList(wrapper);
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

    @Override
    public List<TopicDTO> getTopicsByBoardIdAndPage(Integer boardId, Integer current) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("board", boardId).orderByDesc("gmt_create");
        Page<Topic> page = new Page<>(current, 20, false);
        Page<Topic> result = topicMapper.selectPage(page, wrapper);
        List<Topic> topics = result.getRecords();
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            User user = userMapper.selectById(topic.getCreator());
            Comment comment = commentMapper.selectLastComment(topic.getId());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (comment == null) {
                String time = format.format(topic.getGmtCreate());
                topicDTO.setLastReply(user.getUsername() + "/" + time);
            } else {
                String time = format.format(comment.getGmtCreate());
                topicDTO.setLastReply(userMapper.selectById(comment.getCommentator()).getUsername() + "/" + time);
            }
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topic.getId());
            topicDTO.setViews(topicStatistic.getViews());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    @Override
    public Long getTotalCountByBoardId(Integer boardId) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("board", boardId);
        return topicMapper.selectCount(wrapper);
    }
}
