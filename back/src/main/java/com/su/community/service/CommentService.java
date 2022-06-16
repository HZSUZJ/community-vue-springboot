package com.su.community.service;

import com.su.community.dto.CommentDTO;
import com.su.community.pojo.Comment;
import com.su.community.pojo.Notification;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment, Notification notification);

    List<CommentDTO> getCommentByPage(Long topicId, Integer current, Long userId);

    Long getTotalCountByTopicId(Long topicId);


}
