package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.FolloweeDTO;
import com.su.community.pojo.Follow;
import com.su.community.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping("/addFollow/{followeeId}")
    public String addFollow(@PathVariable("followeeId") Long followeeId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Follow follow = new Follow();
        follow.setFolloweeId(followeeId);
        follow.setUserId(uid);
        followService.addFollow(follow);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/deleteFollow/{followeeId}")
    public String deleteFollow(@PathVariable("followeeId") Long followeeId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Follow follow = new Follow();
        follow.setFolloweeId(followeeId);
        follow.setUserId(uid);
        followService.deleteFollow(follow);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getFollowees")
    public String getFollowees(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<FolloweeDTO> followeeDTOS = followService.getFollowees(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", followeeDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getFans")
    public String getFans(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<FolloweeDTO> followeeDTOS = followService.getFans(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", followeeDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
