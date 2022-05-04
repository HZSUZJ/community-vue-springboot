//package com.su.community.service;
//
//import com.su.community.dto.NotificationDTO;
//import com.su.community.dto.PaginationDTO;
//import com.su.community.dto.QuestionDTO;
//import com.su.community.enums.NotificationStatusEnum;
//import com.su.community.enums.NotificationTypeEnum;
//import com.su.community.mapper.NotificationMapper;
//import com.su.community.mapper.QuestionMapper;
//import com.su.community.mapper.UserMapper;
//import com.su.community.pojo.Notification;
//import com.su.community.pojo.Question;
//import com.su.community.pojo.User;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class NotificationService {
//
//    @Autowired
//    private NotificationMapper notificationMapper;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private QuestionMapper questionMapper;
//
//
//    public PaginationDTO list(Long userId, Integer page, Integer size) {
//        Integer totalCount = notificationMapper.countByUserId(userId);
//        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO();
//        paginationDTO.setPagination(totalCount, page, size);
//        if (page < 1) {
//            page = 1;
//        }
//        if (page > paginationDTO.getTotalPage()) {
//            page = paginationDTO.getTotalPage();
//        }
//        Integer offset = size * (page - 1);
//        List<Notification> notifications = notificationMapper.listByUserId(userId, offset, size);
//        if (notifications.size() == 0) {
//            return paginationDTO;
//        }
//        Set<Long> disUserIds = notifications.stream().map(notify -> notify.getNotifier()).collect(Collectors.toSet());
//        Set<Long> disOuterIds = notifications.stream().map(notify -> notify.getOuterId()).collect(Collectors.toSet());
//        List<Long> userIds=new ArrayList<>();
//        userIds.addAll(disUserIds);
//        List<Long> qustionIds=new ArrayList<>();
//        qustionIds.addAll(disOuterIds);
//        List<User> users=userMapper.findByIds(userIds);//评论或者回复评论的人
//        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
//        List<Question> questions=questionMapper.findByIds(qustionIds);
//        Map<Long, String> questionMap = questions.stream().collect(Collectors.toMap(question -> question.getId(), question -> question.getTitle()));
//        List<NotificationDTO> notificationDTOS =notifications.stream().map(notify->{
//            NotificationDTO notificationDTO=new NotificationDTO();
//            BeanUtils.copyProperties(notify,notificationDTO);
//            notificationDTO.setOuterTitle(questionMap.get(notify.getOuterId()));
//            notificationDTO.setNotifier(userMap.get(notify.getNotifier()));
//            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notify.getType()));
//            return notificationDTO;
//        }).collect(Collectors.toList());
//        paginationDTO.setData(notificationDTOS);
//        return paginationDTO;
//    }
//
//    public Long unreadCount(Long userId) {
//        return notificationMapper.unreadCount(userId);
//    }
//
//    public NotificationDTO read(Long id) {
//        Notification notification=notificationMapper.getById(id);
//        notification.setStatus(NotificationStatusEnum.READ.getStatus());
//        notificationMapper.updateStatus(notification);
//        NotificationDTO notificationDTO=new NotificationDTO();
//        BeanUtils.copyProperties(notification,notificationDTO);
//        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
//        return notificationDTO;
//    }
//}
