package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.service.FollowBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FollowBoardController {

    @Autowired
    private FollowBoardService followBoardService;


    @GetMapping("addFollowBoard/{boardId}")
    public String addFollowBoard(@PathVariable("boardId") Integer boardId) {
        followBoardService.addFollowBoard(boardId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("deleteFollowBoard/{boardId}")
    public String deleteFollowBoard(@PathVariable("boardId") Integer boardId) {
        followBoardService.deleteFollowBoard(boardId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
