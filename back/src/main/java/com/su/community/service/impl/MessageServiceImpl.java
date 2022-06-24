package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.MessageDTO;
import com.su.community.mapper.MessageMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Message;
import com.su.community.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<MessageDTO> getChatLog(Long otherId, Long uid) {
        String conversationId = otherId < uid ? otherId + "_" + uid : uid + "_" + otherId;
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("conversation_id", conversationId).orderByAsc("gmt_create");
        List<Message> messages = messageMapper.selectList(wrapper);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setMe(userMapper.selectById(uid));
            messageDTO.setOther(userMapper.selectById(otherId));
            if (message.getFromId().equals(uid)) {
                messageDTO.setType(2);
            } else {
                messageDTO.setType(1);
            }
            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }

    @Override
    public List<MessageDTO> getConversations(Long uid) {
        List<Message> messages = messageMapper.selectConversations(uid);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            if (message.getFromId().equals(uid)) {
                messageDTO.setUserId(message.getToId());
            } else {
                messageDTO.setUserId(message.getFromId());
            }
            messageDTO.setOther(userMapper.selectById(messageDTO.getUserId()));
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

    @Override
    public void sendMessage(Message message) {
        messageMapper.insert(message);
    }
}
