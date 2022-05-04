package com.su.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser(String username, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public boolean findByToken(String token) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("token",token);

        return true;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }


//    public void createOrUpdate(User user) {
//        User dbUser=userMapper.findByAccountId(user.getAccount_id());
//        if(dbUser==null){
//            user.setGmt_create(System.currentTimeMillis());
//            user.setGmt_modified(System.currentTimeMillis());
//            userMapper.addUser(dbUser);
//        }else{
//            dbUser.setGmt_modified(System.currentTimeMillis());
//            dbUser.setAvatarUrl(user.getAvatarUrl());
//            dbUser.setName(user.getName());
//            dbUser.setToken(user.getToken());
//            userMapper.update(dbUser);
//        }
//
//    }
}
