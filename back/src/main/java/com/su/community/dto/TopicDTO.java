package com.su.community.dto;

import com.su.community.pojo.User;
import lombok.Data;

@Data
public class TopicDTO {
    private Long id;
    private String title;
    private String content;
    private Long creator;
    private Integer notify;
    private String board;
    private Integer views;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
    private Boolean isCollection;
    private Boolean isFollowee;
    private Boolean isMine;
    private Long gmtCreate;
    private Long gmtModified;
    private String lastReply;
    private User user;
}
