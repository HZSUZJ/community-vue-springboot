package com.su.community.controller;

import com.su.community.service.TopicStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicStatisticController {

    @Autowired
    private TopicStatisticService topicStatisticService;

    @GetMapping("/addViews/{id}")
    public String addViews(@PathVariable("id") Long topicId) {
        System.out.println("hahahahaha" + topicId.toString());
        topicStatisticService.increaseViews(topicId);
        return "ok";
    }
}
