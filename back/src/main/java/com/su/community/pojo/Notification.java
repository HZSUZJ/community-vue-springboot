package com.su.community.pojo;

import lombok.Data;

@Data
public class Notification {
    private Long id;
    private Long notifier;
    private Long receiver;
    private Long outerId;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
}