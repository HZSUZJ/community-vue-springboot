package com.su.community.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TopicStatistic {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private Integer views;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
}
