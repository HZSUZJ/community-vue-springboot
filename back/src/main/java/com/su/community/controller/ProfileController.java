package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.ProfileDTO;
import com.su.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getOwnProfile")
    public String getOwnProfile() {
        ProfileDTO profileDTO = profileService.getOwnProfile();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", profileDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @PostMapping("/updateSignature")
    public String updateSignature(@RequestParam("signature") String signature) {
        profileService.updateProfile(signature);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable("userId") Long userId) {
        ProfileDTO profileDTO = profileService.getUserProfile(userId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", profileDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
