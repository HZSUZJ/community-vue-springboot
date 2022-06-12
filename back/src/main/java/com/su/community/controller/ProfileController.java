package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.ProfileDTO;
import com.su.community.pojo.Profile;
import com.su.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getOwnProfile")
    public String getOwnProfile(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        ProfileDTO profileDTO = profileService.getUserProfile(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", profileDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @PostMapping("/updateSignature")
    public String updateSignature(@RequestParam("signature") String signature, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Profile profile = new Profile();
        profile.setSignature(signature);
        profile.setUserId(uid);
        profileService.updateProfile(profile);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
