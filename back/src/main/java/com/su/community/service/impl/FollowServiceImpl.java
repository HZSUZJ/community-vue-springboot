package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.FollowMapper;
import com.su.community.pojo.Follow;
import com.su.community.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public void addFollow(Follow follow) {
        followMapper.insert(follow);
    }

    @Override
    public void deleteFollow(Follow follow) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("followee_id", follow.getFolloweeId()).eq("user_id", follow.getUserId());
        followMapper.delete(wrapper);
    }

    @Override
    public List<Follow> getAllFollowee(Long userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return followMapper.selectList(wrapper);
    }
}
