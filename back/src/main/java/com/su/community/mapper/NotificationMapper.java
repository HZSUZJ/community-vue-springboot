package com.su.community.mapper;

import com.su.community.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {
    void insert(Notification notification);
}
