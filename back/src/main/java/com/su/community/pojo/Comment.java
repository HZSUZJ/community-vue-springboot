package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private Long parentId;
    private Long commentator;
    private String content;
    private Long likeCount;
    private Long dislikeCount;
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

}
