package com.su.community.dto;

import com.su.community.pojo.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private Long outerId;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
