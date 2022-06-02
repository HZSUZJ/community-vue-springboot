package com.su.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardDTO {
    private Integer id;
    private String name;
    private String description;
    private Long postCount;
    private Long todayCount;
    private Boolean isFollowBoard;
    private List<TopicDTO> topics;
}
