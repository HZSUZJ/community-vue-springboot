package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.su.community.dto.ProfileDTO;
import com.su.community.mapper.ProfileMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Profile;
import com.su.community.pojo.User;
import com.su.community.service.ProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ProfileDTO getUserProfile(Long userId) {
        QueryWrapper<Profile> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Profile profile = profileMapper.selectOne(wrapper);
        ProfileDTO profileDTO = new ProfileDTO();
        BeanUtils.copyProperties(profile, profileDTO);
        User user = userMapper.selectById(profile.getUserId());
        profileDTO.setAvatarUrl(user.getAvatarUrl());
        profileDTO.setUsername(user.getUsername());
        return profileDTO;
    }

    @Override
    public void updateProfile(Profile profile) {
        UpdateWrapper<Profile> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", profile.getUserId()).set("signature", profile.getSignature());
        profileMapper.update(null, wrapper);
    }
}
