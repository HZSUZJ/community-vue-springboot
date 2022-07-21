package com.su.community.service;

import com.su.community.dto.NotificationDTO;
import com.su.community.dto.NotificationNumDTO;
import com.su.community.pojo.Notification;

import java.util.List;

public interface NotificationService {
    void addNotification(Notification notification);

    List<NotificationDTO> getAllReply();

    void readReply(Long notifyId);

    void readAllReply();

    NotificationNumDTO unreadCount();
}
