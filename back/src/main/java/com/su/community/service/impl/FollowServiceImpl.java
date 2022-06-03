package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.FolloweeDTO;
import com.su.community.mapper.FollowMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.Follow;
import com.su.community.pojo.Topic;
import com.su.community.pojo.User;
import com.su.community.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;

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

    @Override
    public List<FolloweeDTO> getFollowees(Long userId) {
        List<Follow> follows = getAllFollowee(userId);
        List<FolloweeDTO> followeeDTOS = new ArrayList<>();
        for (Follow follow : follows) {
            FolloweeDTO followeeDTO = new FolloweeDTO();
            followeeDTO.setIsFollowee(true);
            followeeDTO.setId(follow.getFolloweeId());
            User user = userMapper.selectById(follow.getFolloweeId());
            followeeDTO.setUsername(user.getUsername());
            followeeDTO.setTopicNum(topicMapper.selectCount(new QueryWrapper<Topic>()
                    .eq("creator", follow.getFolloweeId())));
            followeeDTO.setFansNum(followMapper.selectCount(new QueryWrapper<Follow>()
                    .eq("followee_id", follow.getFolloweeId())));
            followeeDTO.setAvatarUrl(user.getAvatarUrl());
            followeeDTOS.add(followeeDTO);
        }
        return followeeDTOS;
    }

    @Override
    public List<FolloweeDTO> getFans(Long userId) {
//        得到我关注的人
        List<Follow> myFollows = getAllFollowee(userId);
        List<Long> myFollowIds = new ArrayList<>();
        for (Follow myFollow : myFollows) {
            myFollowIds.add(myFollow.getFolloweeId());
        }

//        得到我的粉丝
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("followee_id", userId);
        List<Follow> follows = followMapper.selectList(wrapper);
        List<FolloweeDTO> followeeDTOS = new ArrayList<>();
        for (Follow follow : follows) {
            FolloweeDTO followeeDTO = new FolloweeDTO();
            followeeDTO.setIsFollowee(myFollowIds.contains(follow.getUserId()));
            followeeDTO.setId(follow.getUserId());
            User user = userMapper.selectById(follow.getUserId());
            followeeDTO.setUsername(user.getUsername());
            followeeDTO.setTopicNum(topicMapper.selectCount(new QueryWrapper<Topic>()
                    .eq("creator", follow.getUserId())));
            followeeDTO.setFansNum(followMapper.selectCount(new QueryWrapper<Follow>()
                    .eq("followee_id", follow.getUserId())));
            followeeDTO.setAvatarUrl(user.getAvatarUrl());
            followeeDTOS.add(followeeDTO);
        }
        return followeeDTOS;
    }
}
