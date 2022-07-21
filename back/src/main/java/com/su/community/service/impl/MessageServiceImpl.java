package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.MessageDTO;
import com.su.community.mapper.MessageMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Message;
import com.su.community.service.MessageService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<MessageDTO> getChatLog(Long otherId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        String conversationId = otherId < userId ? otherId + "_" + userId : userId + "_" + otherId;
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("conversation_id", conversationId).orderByAsc("gmt_create");
        List<Message> messages = messageMapper.selectList(wrapper);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setMe(userMapper.selectById(userId));
            messageDTO.setOther(userMapper.selectById(otherId));
            if (message.getFromId().equals(userId)) {
                messageDTO.setType(2);
            } else {
                messageDTO.setType(1);
            }
            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }

    @Override
    public List<MessageDTO> getConversations() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        List<Message> messages = messageMapper.selectConversations(userId);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            if (message.getFromId().equals(userId)) {
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
    public void sendMessage(Long otherId, String content, Long gmtCreate) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        Message message = new Message();
        message.setContent(content);
        String conversationId = otherId < userId ? otherId + "_" + userId : userId + "_" + otherId;
        message.setConversationId(conversationId);
        message.setFromId(userId);
        message.setToId(otherId);
        message.setGmtCreate(gmtCreate);
        messageMapper.insert(message);
    }
}
