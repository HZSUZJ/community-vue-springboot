package com.su.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.community.dto.BoardDTO;
import com.su.community.pojo.Board;
import com.su.community.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BoardController {


    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public String getBoardList() {
        List<Board> boards = boardService.getBoardList();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", boards);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/board/{boardId}")
    public String getBoardDetail(@PathVariable("boardId") Integer boardId) {
        BoardDTO boardDTO = boardService.getBoardById(boardId);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", boardDTO);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

    @GetMapping("/getFocusBoards")
    public String getFocusBoards() {
        List<BoardDTO> boardDTOS = boardService.getFocusBoards();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", boardDTOS);
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
