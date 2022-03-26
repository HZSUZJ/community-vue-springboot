package com.su.community.controller;

import com.su.community.dto.CommentDTO;
import com.su.community.dto.ResultDTO;
import com.su.community.mapper.CommentMapper;
import com.su.community.pojo.Comment;
import com.su.community.pojo.User;
import com.su.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(2003,"未登录不能进行评论，请先登录");
        }
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGtmCreate(System.currentTimeMillis());
        comment.setGtmModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setContent(commentDTO.getContent());
        commentService.insert(comment);
        Map<Object, Object> objectObjectMap=new HashMap<>();
        objectObjectMap.put("message","成功");
        objectObjectMap.put("code","200");
        return objectObjectMap;
    }
}
