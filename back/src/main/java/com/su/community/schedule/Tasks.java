package com.su.community.schedule;

import com.su.community.service.TopicStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Tasks {

    @Autowired
    private TopicStatisticService topicStatisticService;

    /**
     * @Description: 2小时执行一次
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void viewsTask() {
        topicStatisticService.transViewsFromRedis2DB();
    }
}
