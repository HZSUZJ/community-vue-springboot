package com.su.community.mapper;

import com.su.community.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    Comment findById(Long id);
    List<Comment> findByParentId(Long parentId,int type);
    int updateCommentCount(Comment comment);
}
