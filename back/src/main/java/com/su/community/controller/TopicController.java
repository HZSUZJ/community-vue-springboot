package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/getTopicDetail/{topicId}")
    public String getTopic(@PathVariable("topicId") Long topicId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        TopicDTO topicDTO = topicService.getTopicById(topicId, uid);

        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/myTopics")
    public String myTopic(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<TopicDTO> topicDTOS = topicService.getTopicsByUserId(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/topicsByBoardAndPage/{boardId}/{current}")
    public String topicsByBoardAndPage(@PathVariable("boardId") Integer boardId, @PathVariable("current") Integer current) {
        List<TopicDTO> topicDTOS = topicService.getTopicsByBoardIdAndPage(boardId, current);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getTopicTotalCountByBoardId/{boardId}")
    public String getTopicTotalCountByBoardId(@PathVariable("boardId") Integer boardId) {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("total", topicService.getTotalCountByBoardId(boardId));
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
