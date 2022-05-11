package com.su.community.dto;

import com.su.community.pojo.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long topicId;
    private Long parentId;
    private Long commentator;
    private String content;
    private Long likeCount;
    private Long dislikeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
}
