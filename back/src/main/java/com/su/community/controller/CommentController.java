package com.su.community.controller;

import com.su.community.dto.CommentDTO;
import com.su.community.mapper.CommentMapper;
import com.su.community.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGtmCreate(System.currentTimeMillis());
        comment.setGtmModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setContent(commentDTO.getContent());
        commentMapper.insert(comment);
        return null;
    }
}
