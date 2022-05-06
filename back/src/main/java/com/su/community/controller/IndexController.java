package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping({"/", "/index"})
    public String index() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "200");

        return jsonObject.toJSONString();
    }
}
