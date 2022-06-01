package com.su.community;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.mapper.CollectionMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.pojo.Collection;
import com.su.community.pojo.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackApplicationTests {
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private TopicMapper topicMapper;

    @Test
    public void test() {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 18);
        List<Collection> collections = collectionMapper.selectList(wrapper);
        List<Long> topicIds = new ArrayList<>();
        for (Collection collection : collections) {
            topicIds.add(collection.getTopicId());
        }
        System.out.println(topicIds);
        List<Topic> topics = topicMapper.selectBatchIds(topicIds);
    }
}
