package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.su.community.dto.NotificationDTO;
import com.su.community.dto.NotificationNumDTO;
import com.su.community.mapper.*;
import com.su.community.pojo.*;
import com.su.community.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addNotification(Notification notification) {
        notificationMapper.insert(notification);
    }

    @Override
    public List<NotificationDTO> getAllReply(Long uid) {
        QueryWrapper<Notification> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver", uid).orderByDesc("gmt_create");
        List<Notification> notifications = notificationMapper.selectList(wrapper);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getNotifier().equals(notification.getReceiver())) {
                continue;
            }
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            User user = userMapper.selectById(notification.getNotifier());
            notificationDTO.setUser(user);
            Topic topic = topicMapper.selectById(notification.getTopicId());
            notificationDTO.setTopic(topic);
            Board board = boardMapper.selectById(topic.getBoard());
            notificationDTO.setBoard(board);
            Comment comment = commentMapper.selectById(notification.getCommentId());
            notificationDTO.setComment(comment);
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }

    @Override
    public void readReply(Long uid, Long notifyId) {
        UpdateWrapper<Notification> wrapper = new UpdateWrapper<>();
        wrapper.eq("receiver", uid).eq("id", notifyId).eq("type", "1").eq("status", "0").setSql("status=1");
        notificationMapper.update(null, wrapper);
    }

    @Override
    public void readAllReply(Long uid) {
        UpdateWrapper<Notification> wrapper = new UpdateWrapper<>();
        wrapper.eq("receiver", uid).eq("type", "1").eq("status", "0").setSql("status=1");
        notificationMapper.update(null, wrapper);
    }

    @Override
    public NotificationNumDTO unreadCount(Long uid) {
        NotificationNumDTO notificationNumDTO = new NotificationNumDTO();
        notificationNumDTO.setAtCount(0L);
        notificationNumDTO.setSystemCount(0L);
        notificationNumDTO.setMessageCount(0L);
        QueryWrapper<Notification> queryReplyCountWrapper = new QueryWrapper<>();
        queryReplyCountWrapper.eq("receiver", uid).eq("status", "0").eq("type", "1");
        Long totalCount = 0L;
        Long ReplyCount = notificationMapper.selectCount(queryReplyCountWrapper);
        totalCount += ReplyCount;
        notificationNumDTO.setReplyCount(ReplyCount);
        notificationNumDTO.setTotalCount(totalCount);
        return notificationNumDTO;
    }
}
