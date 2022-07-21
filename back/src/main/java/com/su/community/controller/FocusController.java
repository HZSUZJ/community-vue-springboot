package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class FocusController {
    @Autowired
    private FocusService focusService;

    @GetMapping("/focus/board")
    public String focusBoard() {
        List<TopicDTO> topicDTOS = focusService.focusBoard();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/focus/user")
    public String focusUser() {
        List<TopicDTO> topicDTOS = focusService.focusUser();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/focus/favorite")
    public String focusFavorite() {
        List<TopicDTO> topicDTOS = focusService.focusFavorite();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
