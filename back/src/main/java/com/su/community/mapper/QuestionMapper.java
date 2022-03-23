package com.su.community.mapper;

import com.su.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int create(Question question);

    List<Question> list(Integer offset,Integer size);

    Integer count();

    List<Question> listByUserId(Integer userId, Integer offset, Integer size);

    Integer countByUserId(Integer userId);

    Question getById(Integer id);

    int update(Question question);
}
