package com.su.community.controller;

import com.su.community.dto.CommentCreateDTO;
import com.su.community.dto.CommentDTO;
import com.su.community.dto.ResultDTO;
import com.su.community.pojo.Comment;
import com.su.community.pojo.User;
import com.su.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(2003, "未登录不能进行评论，请先登录");
        }
        if (commentCreateDTO.getContent() == null || "".equals(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(2004, "回复不能为空");
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setContent(commentCreateDTO.getContent());
        commentService.insert(comment);
        Map<Object, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("message", "成功");
        objectObjectMap.put("code", "200");
        return objectObjectMap;
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, 2);
        return ResultDTO.okOf(commentDTOS);
    }


}
