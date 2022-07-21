package com.su.community.service;

import com.su.community.dto.TopicDTO;

import java.util.List;

public interface FavouriteService {
    void addFavourite(Long topicId);

    void deleteFavourite(Long topicId);

    List<TopicDTO> getFavouritesByUserId(Long userId);

    List<TopicDTO> getMyFavourites();
}
