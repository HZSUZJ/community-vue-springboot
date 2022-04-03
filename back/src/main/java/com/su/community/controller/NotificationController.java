package com.su.community.controller;

import com.su.community.dto.NotificationDTO;
import com.su.community.dto.PaginationDTO;
import com.su.community.enums.NotificationTypeEnum;
import com.su.community.mapper.UserMapper;
import com.su.community.pojo.User;
import com.su.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO=notificationService.read(id);
        if(NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()
                ||NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterId();
        }else{
            return "redirect:/";
        }

    }
}
