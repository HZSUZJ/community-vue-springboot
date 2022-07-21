package com.su.community.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.UserDTO;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.User;
import com.su.community.service.RedisService;
import com.su.community.service.UserService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    @Override
    public User queryUser(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public boolean findByToken(String token) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        return true;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public UserDTO getUserBasic(Long userId) {
        User user = userMapper.selectById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public String login(String username, String password) {
        User user = queryUser(username, password);
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("code", "200");
            map.put("message", "登录成功");
            String token = TokenUtil.sign(user);
            redisService.setTokenInRedis(user.getId(), token);
            user.setToken(token);
            updateUser(user);
            map.put("token", token);
            map.put("username", user.getUsername());
            map.put("uid", user.getId());
            map.put("uavatar", user.getAvatarUrl());
        } else {
            map.put("code", "403");
            map.put("message", "用户名或者密码错误");
        }
        jsonObject.put("data", map);
        return jsonObject.toJSONString();
    }

}
