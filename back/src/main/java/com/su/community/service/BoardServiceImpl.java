package com.su.community.service;

import com.su.community.mapper.BoardMapper;
import com.su.community.pojo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectList(null);
    }
}
