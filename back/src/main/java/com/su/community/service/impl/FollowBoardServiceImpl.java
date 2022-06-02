package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.FollowBoardMapper;
import com.su.community.pojo.FollowBoard;
import com.su.community.service.FollowBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowBoardServiceImpl implements FollowBoardService {
    @Autowired
    private FollowBoardMapper followBoardMapper;

    @Override
    public void addFollowBoard(FollowBoard followBoard) {
        followBoardMapper.insert(followBoard);
    }

    @Override
    public void deleteFollowBoard(FollowBoard followBoard) {
        QueryWrapper<FollowBoard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", followBoard.getUserId()).eq("board_id", followBoard.getBoardId());
        followBoardMapper.delete(wrapper);
    }
}
