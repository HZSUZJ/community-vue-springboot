package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.su.community.dto.CommentDTO;
import com.su.community.mapper.CommentMapper;
import com.su.community.mapper.TopicStatisticMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.*;
import com.su.community.service.CommentService;
import com.su.community.service.FollowService;
import com.su.community.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FollowService followService;
    @Autowired
    private TopicStatisticMapper topicStatisticMapper;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void createComment(Comment comment, Notification notification) {
        QueryWrapper<TopicStatistic> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", comment.getTopicId());
        TopicStatistic topicStatistic = topicStatisticMapper.selectOne(wrapper);
        topicStatistic.setCommentCount(topicStatistic.getCommentCount() + 1);
        comment.setFloor(topicStatistic.getCommentCount());
        UpdateWrapper<TopicStatistic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("topic_id", comment.getTopicId()).setSql("comment_count=comment_count+1");
        topicStatisticMapper.update(null, updateWrapper);
        commentMapper.insert(comment);
        notification.setCommentId(comment.getId());
        if (!notification.getNotifier().equals(notification.getReceiver())) {
            notificationService.addNotification(notification);
        }
    }

    @Override
    public List<CommentDTO> getCommentByPage(Long topicId, Integer current, Long userId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", topicId).orderByAsc("gmt_create");
        Page<Comment> page = new Page<>(current, 10, false);
        Page<Comment> result = commentMapper.selectPage(page, wrapper);
        List<Comment> comments = result.getRecords();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        List<Follow> follows = followService.getAllFollowee(userId);
        List<Long> followeeIds = new ArrayList<>();
        for (Follow follow : follows) {
            followeeIds.add(follow.getFolloweeId());
        }
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            User user = userMapper.selectById(comment.getCommentator());
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setIsFollowee(followeeIds.contains(user.getId()));
            commentDTO.setUser(user);
            commentDTO.setIsMine(user.getId().equals(userId));
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    @Override
    public Long getTotalCountByTopicId(Long topicId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", topicId);
        return commentMapper.selectCount(wrapper);
    }


}
