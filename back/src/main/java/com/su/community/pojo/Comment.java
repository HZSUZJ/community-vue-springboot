package com.su.community.pojo;

import lombok.Data;

@Data
public class Comment {
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private Long gtmCreate;
    private Long gtmModified;
    private Long likeCount;
    private String content;
}
