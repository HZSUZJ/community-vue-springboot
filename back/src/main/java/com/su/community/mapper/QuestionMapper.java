package com.su.community.mapper;

import com.su.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface QuestionMapper {
    int create(Question question);

    List<Question> list(Integer offset,Integer size);

    Integer count();

    List<Question> listByUserId(Long userId, Integer offset, Integer size);

    Integer countByUserId(Long userId);

    Question getById(Long id);

    int update(Question question);

    int updateViewCount(Question question);

    int updateCommentCount(Question question);

    List<Question> selectRelated(Question question);

    List<Question> findByIds(List<Long> ids);

}
