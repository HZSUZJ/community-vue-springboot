package com.su.community.service;

import com.su.community.mapper.CommentMapper;
import com.su.community.mapper.QuestionMapper;
import com.su.community.pojo.Comment;
import com.su.community.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment){
        /**
         * 1为回复问题
         * 2为回复评论
         */

        if(comment.getType()==1){
            //回复问题
            Question question=questionMapper.getById(comment.getParentId());
            //添加异常
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapper.updateCommentCount(question);

        }else{//回复评论
            Comment dbComment=commentMapper.findById(comment.getParentId());
            //添加异常
            commentMapper.insert(comment);
        }



    }

}
