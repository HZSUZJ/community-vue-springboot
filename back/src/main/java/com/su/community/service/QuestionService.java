package com.su.community.service;

import com.su.community.dto.PaginationDTO;
import com.su.community.dto.QuestionDTO;
import com.su.community.mapper.QuestionMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Question;
import com.su.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }


    public PaginationDTO list(Long userId, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByUserId(userId);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question=questionMapper.getById(id);
        User user=userMapper.findById(question.getCreator());
        QuestionDTO questionDTO= new QuestionDTO();
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            question.setCommentCount(0);
            questionMapper.create(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
    public void  incView(Long id){
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionMapper.updateViewCount(question);
    }


    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        String[] tags=queryDTO.getTag().split(",");
        String regexTg= Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setTag(regexTg);
        question.setId(queryDTO.getId());
        List<Question> questions=questionMapper.selectRelated(question);
        List<QuestionDTO>questionDTOS=questions.stream().map(q->{
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
