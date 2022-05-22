package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Follow {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long followeeId;
    private Long userId;
}
