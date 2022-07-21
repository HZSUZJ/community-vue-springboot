package com.su.community.service;

import com.su.community.dto.TopicDTO;

import java.util.List;

public interface FocusService {
    List<TopicDTO> focusBoard();

    List<TopicDTO> focusUser();

    List<TopicDTO> focusFavorite();
}
