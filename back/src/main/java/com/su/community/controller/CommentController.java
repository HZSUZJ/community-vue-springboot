package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.CommentDTO;
import com.su.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("/postComment")
    public String postComment(Long topicId, Long parentId, String content, Long topicUserId) {
        commentService.createComment(topicId, parentId, content, topicUserId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getAllComment/{topicId}/{current}")
    public String getAllComment(@PathVariable("topicId") Long topicId, @PathVariable("current") Integer current) {
        List<CommentDTO> commentDTOS = commentService.getCommentByPage(topicId, current);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", commentDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getCommentTotalCount/{topicId}")
    public String getCommentTotalCount(@PathVariable("topicId") Long topicId) {
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("total", commentService.getTotalCountByTopicId(topicId));
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
