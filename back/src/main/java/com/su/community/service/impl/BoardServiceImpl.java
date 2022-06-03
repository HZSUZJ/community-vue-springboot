package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.BoardDTO;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.FollowBoardMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.*;
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
    @Autowired
    private FollowBoardMapper followBoardMapper;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectList(null);
    }

    @Override
    public BoardDTO getBoardById(Integer boardId, Long uid) {
        Board board = boardMapper.selectById(boardId);
        BoardDTO boardDTO = new BoardDTO();
        BeanUtils.copyProperties(board, boardDTO);
        QueryWrapper<FollowBoard> boardQueryWrapper = new QueryWrapper<>();
        boardQueryWrapper.eq("user_id", uid).eq("board_id", boardId);
        FollowBoard followBoard = followBoardMapper.selectOne(boardQueryWrapper);
        boardDTO.setIsFollowBoard(followBoard != null);
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

    @Override
    public List<BoardDTO> getFocusBoards(Long uid) {
        QueryWrapper<FollowBoard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        List<FollowBoard> followBoards = followBoardMapper.selectList(wrapper);
        List<Integer> boardIds = new ArrayList<>();
        for (FollowBoard followBoard : followBoards) {
            boardIds.add(followBoard.getBoardId());
        }
        QueryWrapper<Board> queryWrapper = new QueryWrapper<>();
        List<Board> boards = new ArrayList<>();
        if (boardIds.size() != 0) {
            queryWrapper.in("id", boardIds);
            boards = boardMapper.selectList(queryWrapper);
        }
        List<BoardDTO> boardDTOS = new ArrayList<>();
        for (Board board : boards) {
            BoardDTO boardDTO = new BoardDTO();
            BeanUtils.copyProperties(board, boardDTO);
            boardDTO.setIsFollowBoard(true);
            boardDTOS.add(boardDTO);
        }
        return boardDTOS;
    }
}
