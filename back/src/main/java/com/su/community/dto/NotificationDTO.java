package com.su.community.dto;

import com.su.community.pojo.Board;
import com.su.community.pojo.Comment;
import com.su.community.pojo.Topic;
import com.su.community.pojo.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long notifier;
    private Long receiver;
    private Long commentId;
    private Integer type;
    private Integer status;
    private Long gmtCreate;
    private User user;
    private Topic topic;
    private Board board;
    private Comment comment;
}
