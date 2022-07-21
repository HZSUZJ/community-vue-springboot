package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.MessageDTO;
import com.su.community.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message/user/{otherId}")
    public String getChatLog(@PathVariable("otherId") Long otherId) {
        List<MessageDTO> messageDTOS = messageService.getChatLog(otherId);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", messageDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }


    @GetMapping("/message/conversations")
    public String getConversations() {
        List<MessageDTO> messageDTOS = messageService.getConversations();
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
                              @RequestParam("gmtCreate") Long gmtCreate) {
        messageService.sendMessage(otherId, content, gmtCreate);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
