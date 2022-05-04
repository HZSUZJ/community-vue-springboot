package com.su.community.service;

import com.su.community.pojo.User;

public interface UserService {
    User queryUser(String username,String password);
    boolean findByToken(String token);
    void updateUser(User user);
}
