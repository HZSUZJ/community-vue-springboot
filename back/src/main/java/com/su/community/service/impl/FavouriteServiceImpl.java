package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.BoardMapper;
import com.su.community.mapper.FavouriteMapper;
import com.su.community.mapper.TopicMapper;
import com.su.community.pojo.Favourite;
import com.su.community.pojo.Topic;
import com.su.community.service.FavouriteService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteMapper favouriteMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void addFavourite(Long topicId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        Favourite favourite = new Favourite();
        favourite.setTopicId(topicId);
        favourite.setUserId(userId);
        favouriteMapper.insert(favourite);
    }

    @Override
    public void deleteFavourite(Long topicId) {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        Favourite favourite = new Favourite();
        favourite.setTopicId(topicId);
        favourite.setUserId(userId);
        QueryWrapper<Favourite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", favourite.getUserId()).eq("topic_id", favourite.getTopicId());
        favouriteMapper.delete(wrapper);
    }

    @Override
    public List<TopicDTO> getFavouritesByUserId(Long userId) {
        QueryWrapper<Favourite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).orderByDesc("gmt_create");
        List<Favourite> collections = favouriteMapper.selectList(wrapper);
        List<Long> topicIds = new ArrayList<>();
        for (Favourite collection : collections) {
            topicIds.add(collection.getTopicId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        List<Topic> topics = new ArrayList<>();
        if (topicIds.size() != 0) {
            topicQueryWrapper.in("id", topicIds);
            topics = topicMapper.selectList(topicQueryWrapper);
        }
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    @Override
    public List<TopicDTO> getMyFavourites() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        return getFavouritesByUserId(userId);
    }
}
