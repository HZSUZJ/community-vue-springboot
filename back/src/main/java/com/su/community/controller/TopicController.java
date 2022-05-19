package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/getNewTopics")
    public String getAllTopics() {  //还未写懒加载
        List<TopicDTO> topicDTOS = topicService.getAllTopic();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getTopicDetail/{id}")
    public String getTopic(@PathVariable("id") Long topicId) {
        TopicDTO topicDTO = topicService.getTopicById(topicId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
    

}
