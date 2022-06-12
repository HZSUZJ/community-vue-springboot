package com.su.community.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private String username;
    private String avatarUrl;
    private Integer sex;
    private String signature;
    private Integer topicNum;
    private Integer fansNum;
    private Long gmtRegistration;
    private Long gmtLogin;
}
