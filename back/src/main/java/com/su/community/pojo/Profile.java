package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Profile {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer sex;
    private String signature;
    private Integer topicNum;
    private Integer fansNum;
    private Long gmtRegistration;
    private Long gmtLogin;
}
