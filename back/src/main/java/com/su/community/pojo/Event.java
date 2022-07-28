package com.su.community.pojo;

import lombok.Data;

@Data
public class Event {
    // 事件类型
    private String topic;
    private Notification notification;
}
