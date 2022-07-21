package com.su.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.community.dto.TopicDTO;
import com.su.community.mapper.*;
import com.su.community.pojo.*;
import com.su.community.service.FocusService;
import com.su.community.service.TopicStatisticService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private FavouriteMapper collectionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private TopicStatisticService topicStatisticService;
    @Autowired
    private FollowBoardMapper followBoardMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private HttpServletRequest request;

    @Override
    public List<TopicDTO> focusBoard() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        QueryWrapper<FollowBoard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<FollowBoard> followBoards = followBoardMapper.selectList(wrapper);
        List<Integer> boardIds = new ArrayList<>();
        for (FollowBoard followBoard : followBoards) {
            boardIds.add(followBoard.getBoardId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        List<Topic> topics = new ArrayList<>();
        if (boardIds.size() != 0) {
            topicQueryWrapper.in("board", boardIds).orderByDesc("gmt_modified");
            topics = topicMapper.selectList(topicQueryWrapper);
        }

        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            User user = userMapper.selectById(topic.getCreator());
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topicDTO.getId());
            topicDTO.setViews(topicStatisticService.getViews(topicDTO.getId()));
            topicDTO.setLikeCount(topicStatistic.getLikeCount());
            topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    @Override
    public List<TopicDTO> focusUser() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Follow> follows = followMapper.selectList(wrapper);
        List<Long> userIds = new ArrayList<>();
        for (Follow follow : follows) {
            userIds.add(follow.getFolloweeId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        List<Topic> topics = new ArrayList<>();
        if (userIds.size() != 0) {
            topicQueryWrapper.in("creator", userIds).orderByDesc("gmt_modified");
            topics = topicMapper.selectList(topicQueryWrapper);
        }
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            User user = userMapper.selectById(topic.getCreator());
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topicDTO.getId());
            topicDTO.setViews(topicStatisticService.getViews(topicDTO.getId()));
            topicDTO.setLikeCount(topicStatistic.getLikeCount());
            topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    @Override
    public List<TopicDTO> focusFavorite() {
        Long userId = tokenUtil.getUserIdFromRequest(request);
        QueryWrapper<Favourite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Favourite> collections = collectionMapper.selectList(wrapper);
        List<Long> topicIds = new ArrayList<>();
        for (Favourite collection : collections) {
            topicIds.add(collection.getTopicId());
        }
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        List<Topic> topics = new ArrayList<>();
        if (topicIds.size() != 0) {
            topicQueryWrapper.in("id", topicIds).orderByDesc("gmt_modified");
            topics = topicMapper.selectList(topicQueryWrapper);
        }
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            User user = userMapper.selectById(topic.getCreator());
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            String boardName = boardMapper.selectById(topic.getBoard()).getName();
            topicDTO.setBoard(boardName);
            topicDTO.setUser(user);
            TopicStatistic topicStatistic = topicStatisticService.getTopicStatistic(topicDTO.getId());
            topicDTO.setViews(topicStatisticService.getViews(topicDTO.getId()));
            topicDTO.setLikeCount(topicStatistic.getLikeCount());
            topicDTO.setDislikeCount(topicStatistic.getDislikeCount());
            topicDTO.setCommentCount(topicStatistic.getCommentCount());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }
}
