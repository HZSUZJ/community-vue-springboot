package com.su.community.service;

import com.su.community.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO getOwnProfile();

    void updateProfile(String signature);

    ProfileDTO getUserProfile(Long userId);


}
