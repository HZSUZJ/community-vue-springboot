package com.su.community.dto;

import lombok.Data;

@Data
public class NotificationNumDTO {
    private Long replyCount;
    private Long atCount;
    private Long systemCount;
    private Long messageCount;
    private Long totalCount;
}
