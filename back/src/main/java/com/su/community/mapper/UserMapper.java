package com.su.community.mapper;

import com.su.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);

    User findByToken(String token);

    User findById(Long id);

    User findByAccountId(String accountId);

    int update(User dbUser);
}
