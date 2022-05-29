package com.su.community.service;

import com.su.community.dto.BoardDTO;
import com.su.community.pojo.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList();

    BoardDTO getBoardById(Integer boardId);
}
