package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.su.community.dto.ProfileDTO;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.ProfileMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Profile;
import com.su.community.pojo.User;
import com.su.community.service.ProfileService;
import com.su.community.service.TopicService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public ProfileDTO getOwnProfile() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
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
    public void updateProfile(String signature) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        Profile profile = new Profile();
        profile.setSignature(signature);
        profile.setUserId(userId);
        UpdateWrapper<Profile> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", profile.getUserId()).set("signature", profile.getSignature());
        profileMapper.update(null, wrapper);
    }

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
        List<TopicDTO> topicDTOS = topicService.getTopicsByUserId(userId);
        profileDTO.setTopics(topicDTOS);
        return profileDTO;
    }
}
