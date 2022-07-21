package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PublishController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/publish")
    public String doPublish(String title, String content, Boolean notify, Integer board) {
        Long topicId = topicService.creatTopic(title, content, notify, board);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("topicId", topicId);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
