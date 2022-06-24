package com.su.community.dto;

import com.su.community.pojo.User;
import lombok.Data;

@Data
public class MessageDTO {
    private Long id;
    private Long fromId;
    private Long toId;
    private String conversationId;
    private String content;
    private Integer status;
    private Long gmtCreate;
    //1是对方,2是我
    private Integer type;
    //列表人的id
    private Long userId;
    private User me;
    private User other;
}
