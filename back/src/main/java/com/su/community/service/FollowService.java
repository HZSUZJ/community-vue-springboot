package com.su.community.service;

import com.su.community.pojo.Follow;

import java.util.List;

public interface FollowService {
    void addFollow(Follow follow);

    void deleteFollow(Follow follow);

    List<Follow> getAllFollowee(Long userId);
}