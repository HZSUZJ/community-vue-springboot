package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/addCollection/{topicId}")
    public String addFavourite(@PathVariable("topicId") Long topicId) {
        favouriteService.addFavourite(topicId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/deleteCollection/{topicId}")
    public String deleteFavourite(@PathVariable("topicId") Long topicId) {
        favouriteService.deleteFavourite(topicId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/myCollections")
    public String myFavourite() {
        List<TopicDTO> topicDTOS = favouriteService.getMyFavourites();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }


}
