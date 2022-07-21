package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.FollowBoardMapper;
import com.su.community.pojo.FollowBoard;
import com.su.community.service.FollowBoardService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class FollowBoardServiceImpl implements FollowBoardService {
    @Autowired
    private FollowBoardMapper followBoardMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void addFollowBoard(Integer boardId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        FollowBoard followBoard = new FollowBoard();
        followBoard.setBoardId(boardId);
        followBoard.setUserId(userId);
        followBoardMapper.insert(followBoard);
    }

    @Override
    public void deleteFollowBoard(Integer boardId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        FollowBoard followBoard = new FollowBoard();
        followBoard.setBoardId(boardId);
        followBoard.setUserId(userId);
        QueryWrapper<FollowBoard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", followBoard.getUserId()).eq("board_id", followBoard.getBoardId());
        followBoardMapper.delete(wrapper);
    }
}
