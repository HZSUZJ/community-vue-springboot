package com.su.community.service;

import com.su.community.dto.TopicDTO;
import com.su.community.pojo.Collection;

import java.util.List;

public interface CollectionService {
    void addCollection(Collection collection);

    void deleteCollection(Collection collection);

    List<TopicDTO> getCollectionsByUserId(Long uid);
}
