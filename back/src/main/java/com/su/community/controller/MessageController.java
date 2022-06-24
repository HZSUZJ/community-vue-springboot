package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.MessageDTO;
import com.su.community.pojo.Message;
import com.su.community.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message/user/{otherId}")
    public String getChatLog(@PathVariable("otherId") Long otherId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<MessageDTO> messageDTOS = messageService.getChatLog(otherId, uid);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", messageDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }


    @GetMapping("/message/conversations")
    public String getConversations(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<MessageDTO> messageDTOS = messageService.getConversations(uid);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", messageDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @PostMapping("/message/send")
    public String sendMessage(@RequestParam("otherId") Long otherId,
                              @RequestParam("content") String content,
                              @RequestParam("gmtCreate") Long gmtCreate,
                              HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Message message = new Message();
        message.setContent(content);
        String conversationId = otherId < uid ? otherId + "_" + uid : uid + "_" + otherId;
        message.setConversationId(conversationId);
        message.setFromId(uid);
        message.setToId(otherId);
        message.setGmtCreate(gmtCreate);
        messageService.sendMessage(message);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
