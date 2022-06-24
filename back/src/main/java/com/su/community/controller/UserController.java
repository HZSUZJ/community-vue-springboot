package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.UserDTO;
import com.su.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user/basic/{userId}")
    public String getUserBasic(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.getUserBasic(userId);
        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map.put("code", 200);
        map.put("data", userDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
