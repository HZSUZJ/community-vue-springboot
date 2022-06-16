package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.NotificationDTO;
import com.su.community.dto.NotificationNumDTO;
import com.su.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/reply")
    public String reply(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<NotificationDTO> notificationDTOS = notificationService.getAllReply(uid);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", notificationDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/notification/readReply/{notifyId}")
    public String readReply(@PathVariable("notifyId") Long notifyId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        notificationService.readReply(uid, notifyId);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/notification/readAllReply")
    public String readAllReply(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        notificationService.readAllReply(uid);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/notification/unreadCount")
    public String unreadCount(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        NotificationNumDTO notificationNumDTO = notificationService.unreadCount(uid);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", notificationNumDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
