package com.su.community.service;

import com.su.community.dto.FolloweeDTO;
import com.su.community.pojo.Follow;

import java.util.List;

public interface FollowService {
    void addFollow(Follow follow);

    void deleteFollow(Follow follow);

    List<Follow> getAllFollowee(Long userId);

    List<FolloweeDTO> getFollowees(Long userId);

    List<FolloweeDTO> getFans(Long userId);
}
