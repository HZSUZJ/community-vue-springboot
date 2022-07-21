package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Favourite {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long topicId;
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;
}
