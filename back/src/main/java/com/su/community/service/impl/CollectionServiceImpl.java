package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.CollectionMapper;
import com.su.community.pojo.Collection;
import com.su.community.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;


    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insert(collection);
    }

    @Override
    public void deleteCollection(Collection collection) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", collection.getUserId()).eq("topic_id", collection.getTopicId());
        collectionMapper.delete(wrapper);
    }
}
