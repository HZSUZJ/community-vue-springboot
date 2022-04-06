package com.su.community;

import com.su.community.dto.QuestionQueryDTO;
import com.su.community.mapper.QuestionMapper;
import com.su.community.pojo.Question;
import com.su.community.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackApplicationTests {

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void contextLoads() {
        QuestionQueryDTO questionQueryDTO=new QuestionQueryDTO();
        questionQueryDTO.setSearch("java");
        questionQueryDTO.setOffset(0);
        questionQueryDTO.setSize(5);

        List<Question> questions = questionMapper.selectBySearch(questionQueryDTO);
        System.out.println(questions);

    }

}