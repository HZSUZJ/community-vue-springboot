package com.su.community.service;

import com.su.community.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    //void createComment(Comment comment, Notification notification);
    void createComment(Long topicId, Long parentId, String content, Long topicUserId);

    List<CommentDTO> getCommentByPage(Long topicId, Integer current);

    Long getTotalCountByTopicId(Long topicId);


}
