package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.pojo.Topic;
import com.su.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class PublishController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/publish")
    public String doPublish(String title,
                            String content,
                            boolean notify,
                            Integer board,
                            HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNotify(notify ? 1 : 0);
        topic.setBoard(board);
        topic.setCreator(uid);
        Long topicId = topicService.creatTopic(topic);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("topicId", topicId);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
