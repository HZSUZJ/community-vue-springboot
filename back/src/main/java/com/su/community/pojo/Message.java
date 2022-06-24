package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Message {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long fromId;
    private Long toId;
    private String conversationId;
    private String content;
    private Integer status;
    private Long gmtCreate;
}
