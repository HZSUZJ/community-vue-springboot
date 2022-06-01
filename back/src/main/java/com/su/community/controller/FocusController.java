package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class FocusController {
    @Autowired
    private FocusService focusService;

    @GetMapping("/focus/board")
    public String focusBoard(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");

        return null;
    }

    @GetMapping("/focus/user")
    public String focusUser(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<TopicDTO> topicDTOS = focusService.focusUser(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/focus/favorite")
    public String focusFavorite(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<TopicDTO> topicDTOS = focusService.focusFavorite(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
