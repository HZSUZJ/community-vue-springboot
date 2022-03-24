package com.su.community.mapper;

import com.su.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
}
