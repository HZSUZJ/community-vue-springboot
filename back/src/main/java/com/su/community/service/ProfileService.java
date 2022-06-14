package com.su.community.service;

import com.su.community.dto.ProfileDTO;
import com.su.community.pojo.Profile;

public interface ProfileService {
    ProfileDTO getOwnProfile(Long userId);

    void updateProfile(Profile profile);

    ProfileDTO getUserProfile(Long userId);


}
