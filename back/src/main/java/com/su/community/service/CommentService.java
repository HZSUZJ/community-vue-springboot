//package com.su.community.service;
//
//import com.su.community.dto.CommentDTO;
//import com.su.community.enums.NotificationStatusEnum;
//import com.su.community.enums.NotificationTypeEnum;
//import com.su.community.mapper.CommentMapper;
//import com.su.community.mapper.NotificationMapper;
//import com.su.community.mapper.QuestionMapper;
//import com.su.community.mapper.UserMapper;
//import com.su.community.pojo.Comment;
//import com.su.community.pojo.Notification;
//import com.su.community.pojo.Question;
//import com.su.community.pojo.User;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CommentService {
//
//    @Autowired
//    private CommentMapper commentMapper;
//    @Autowired
//    private QuestionMapper questionMapper;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private NotificationMapper notificationMapper;
//
//    @Transactional
//    public void insert(Comment comment) {
//        /**
//         * 1为回复问题
//         * 2为回复评论
//         */
//
//        if (comment.getType() == 1) {
//            //回复问题
//            Question question = questionMapper.getById(comment.getParentId());
//            //添加异常
//            commentMapper.insert(comment);
//            question.setCommentCount(1);
//            questionMapper.updateCommentCount(question);
//            //创建通知
//            createNotify(comment,question.getCreator(),NotificationTypeEnum.REPLY_QUESTION,question.getId());
//
//        } else {//回复评论
//            Comment dbComment = commentMapper.findById(comment.getParentId());
//            //添加异常
//            commentMapper.insert(comment);
//            Comment parentComment = new Comment();
//            parentComment.setId(comment.getParentId());
//            parentComment.setCommentCount(1);
//            commentMapper.updateCommentCount(parentComment);
//            //创建通知
//            createNotify(comment,dbComment.getCommentator(),NotificationTypeEnum.REPLY_COMMENT,dbComment.getParentId());
//        }
//    }
//    private void createNotify(Comment comment,Long receiver,NotificationTypeEnum notificationTypeEnum,Long questionId){
//        Notification notification=new Notification();
//        notification.setGmtCreate(System.currentTimeMillis());
//        notification.setType(notificationTypeEnum.getType());
//        notification.setOuterId(questionId);
//        notification.setNotifier(comment.getCommentator());
//        notification.setReceiver(receiver);
//        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
//        notificationMapper.insert(notification);
//    }
//
//    public List<CommentDTO> listByTargetId(Long id, Integer type) {
//        List<Comment> comments = commentMapper.findByParentId(id, type);
//        if (comments.size() == 0) {
//            return new ArrayList<>();
//        }
//        //获取去重的评论人
//        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
//        List<Long> userIds = new ArrayList<>();
//        userIds.addAll(commentators);
//
//        //获取评论人并转化为Map
//        List<User> users = userMapper.findByIds(userIds);
//        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
//
//        //转换comment为commentDTO
//        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
//            CommentDTO commentDTO = new CommentDTO();
//            BeanUtils.copyProperties(comment, commentDTO);
//            commentDTO.setUser(userMap.get(comment.getCommentator()));
//            return commentDTO;
//        }).collect(Collectors.toList());
//        return commentDTOS;
//    }
//
//
//}
