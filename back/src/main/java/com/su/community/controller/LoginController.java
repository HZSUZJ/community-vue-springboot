package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.pojo.User;
import com.su.community.service.UserService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.queryUser(username, password);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("code", "200");
            map.put("message", "登录成功");
            String token = TokenUtil.sign(user);
            user.setToken(token);
            userService.updateUser(user);
            map.put("token", token);
            map.put("username", user.getUsername());
            map.put("uid", user.getId());
            map.put("uavatar", user.getAvatarUrl());
        } else {
            map.put("code", "403");
            map.put("message", "用户名或者密码错误");
        }
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }
}
