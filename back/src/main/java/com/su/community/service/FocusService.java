package com.su.community.service;

import com.su.community.dto.TopicDTO;

import java.util.List;

public interface FocusService {
    List<TopicDTO> focusBoard(Long uid);

    List<TopicDTO> focusUser(Long uid);

    List<TopicDTO> focusFavorite(Long uid);
}
