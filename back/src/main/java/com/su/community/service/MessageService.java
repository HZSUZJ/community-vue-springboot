package com.su.community.service;

import com.su.community.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> getChatLog(Long otherId);

    List<MessageDTO> getConversations();

    void sendMessage(Long otherId, String content, Long gmtCreate);
}
