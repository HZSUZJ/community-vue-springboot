package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.TopicDTO;
import com.su.community.pojo.Collection;
import com.su.community.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/addCollection/{topicId}")
    public String addCollection(@PathVariable("topicId") Long topicId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Collection collection = new Collection();
        collection.setTopicId(topicId);
        collection.setUserId(uid);
        collectionService.addCollection(collection);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/deleteCollection/{topicId}")
    public String deleteCollection(@PathVariable("topicId") Long topicId, HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        Collection collection = new Collection();
        collection.setTopicId(topicId);
        collection.setUserId(uid);
        collectionService.deleteCollection(collection);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/myCollections")
    public String myCollection(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("UID");
        List<TopicDTO> topicDTOS = collectionService.getCollectionsByUserId(uid);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", topicDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }


}
