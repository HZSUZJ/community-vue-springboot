package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.BoardDTO;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Board;
import com.su.community.pojo.Topic;
import com.su.community.pojo.TopicStatistic;
import com.su.community.pojo.User;
import com.su.community.service.BoardService;
import com.su.community.service.TopicStatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicStatisticService topicStatisticService;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectList(null);
    }

    @Override
    public BoardDTO getBoardById(Integer boardId) {
        Board board = boardMapper.selectById(boardId);
        BoardDTO boardDTO = new BoardDTO();
        BeanUtils.copyProperties(board, boardDTO);

        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("board", boardId);
        List<Topic> topics = topicMapper.selectList(wrapper);
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            User user = userMapper.selectById(topic.getCreator());
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topic.getId());
            topicDTO.setViews(topicStatistic.getViews());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        boardDTO.setTopics(topicDTOS);
        return boardDTO;
    }
}
