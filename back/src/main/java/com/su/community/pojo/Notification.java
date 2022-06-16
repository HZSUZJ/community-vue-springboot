package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Notification {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long notifier;
    //接收通知者
    private Long receiver;
    private Long topicId;
    private Long commentId;
    //通知类型  1代表回复评论
    private Integer type;
    //是否已读
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

}
