package com.su.community.service;

import com.su.community.dto.MessageDTO;
import com.su.community.pojo.Message;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getChatLog(Long otherId, Long uid);

    List<MessageDTO> getConversations(Long uid);

    void sendMessage(Message message);
}
