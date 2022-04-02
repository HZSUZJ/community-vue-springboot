package com.su.community.service;

import com.su.community.dto.PaginationDTO;
import com.su.community.dto.QuestionDTO;
import com.su.community.mapper.NotificationMapper;
import com.su.community.pojo.Question;
import com.su.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;



    public PaginationDTO list(Long userId, Integer page, Integer size) {
//        Integer totalCount = notificationMapper.countByUserId(userId);
//        PaginationDTO paginationDTO = new PaginationDTO();
//        paginationDTO.setPagination(totalCount, page, size);
//        if(page<1){
//            page=1;
//        }
//        if(page>paginationDTO.getTotalPage()){
//            page=paginationDTO.getTotalPage();
//        }
//        Integer offset = size * (page - 1);
//        List<Question> questions = notificationMapper.listByUserId(userId,offset, size);
//        List<QuestionDTO> questionDTOS = new ArrayList<>();
//
//        for (Question question : questions) {
//            User user = userMapper.findById(question.getCreator());
//            QuestionDTO questionDTO = new QuestionDTO();
//            BeanUtils.copyProperties(question, questionDTO);
//            questionDTO.setUser(user);
//            questionDTOS.add(questionDTO);
//        }
//        paginationDTO.setQuestions(questionDTOS);
//        return paginationDTO;
        return null;
    }
}
