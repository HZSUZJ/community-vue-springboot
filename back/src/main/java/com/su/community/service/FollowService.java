package com.su.community.service;

import com.su.community.dto.FolloweeDTO;
import com.su.community.pojo.Follow;

import java.util.List;

public interface FollowService {
    void addFollow(Long followeeId);

    void deleteFollow(Long followeeId);

    List<Follow> getAllFollowee();

    List<FolloweeDTO> getFollowees();

    List<FolloweeDTO> getFans();
}
