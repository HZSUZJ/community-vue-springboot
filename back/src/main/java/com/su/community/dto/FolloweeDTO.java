package com.su.community.dto;

import lombok.Data;

@Data
public class FolloweeDTO {
    private Long id;
    private String username;
    private Long topicNum;
    private Long fansNum;
    private Boolean isFollowee;
    private String avatarUrl;
}
