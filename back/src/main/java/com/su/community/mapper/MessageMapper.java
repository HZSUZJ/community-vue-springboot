package com.su.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.su.community.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    List<Message> selectConversations(Long uid);
}
