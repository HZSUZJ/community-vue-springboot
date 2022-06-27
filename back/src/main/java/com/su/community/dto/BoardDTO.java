package com.su.community.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private Integer id;
    private String name;
    private String description;
    private Long postCount;
    private Long todayCount;
    private Boolean isFollowBoard;
}
