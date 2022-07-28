package com.su.community.event;

import com.alibaba.fastjson.JSONObject;
import com.su.community.pojo.Event;
import com.su.community.pojo.Notification;
import com.su.community.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "topic_notification", groupId = "notification-group")
    public void handleNotification(ConsumerRecord<String, String> record, Acknowledgment ack) {
        Event event = JSONObject.parseObject(record.value(), Event.class);
        Notification notification = event.getNotification();
        notificationService.addNotification(notification);
        ack.acknowledge();
    }
}
