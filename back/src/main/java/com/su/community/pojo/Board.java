package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Board {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Long postCount;
    private Long todayCount;
}
