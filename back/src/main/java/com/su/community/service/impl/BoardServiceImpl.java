package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.BoardDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.FollowBoardMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Board;
import com.su.community.pojo.FollowBoard;
import com.su.community.service.BoardService;
import com.su.community.service.TopicStatisticService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectList(null);
    }

    @Override
    public BoardDTO getBoardById(Integer boardId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        Board board = boardMapper.selectById(boardId);
        BoardDTO boardDTO = new BoardDTO();
        BeanUtils.copyProperties(board, boardDTO);
        QueryWrapper<FollowBoard> boardQueryWrapper = new QueryWrapper<>();
        boardQueryWrapper.eq("user_id", userId).eq("board_id", boardId);
        FollowBoard followBoard = followBoardMapper.selectOne(boardQueryWrapper);
        boardDTO.setIsFollowBoard(followBoard != null);
        return boardDTO;
    }

    @Override
    public List<BoardDTO> getFocusBoards() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        QueryWrapper<FollowBoard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
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
