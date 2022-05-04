package com.su.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.su.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
//    int addUser(User user);
//
//    User findByToken(String token);
//
//    User findById(Long id);
//
//    User findByAccountId(String accountId);
//
//    int update(User dbUser);
//
//    List<User> findByIds(List<Long> ids);

}
