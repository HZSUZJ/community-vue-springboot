package com.su.community.service;

import com.su.community.dto.CommentDTO;
import com.su.community.pojo.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);

    List<CommentDTO> getCommentByPage(Long topicId, Integer current);

    Long getTotalCountByTopicId(Long topicId);

}
