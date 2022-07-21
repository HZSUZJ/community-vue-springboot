package com.su.community.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.su.community.service.RedisService;
import com.su.community.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        Long userId = tokenUtil.getUserIdFromRequest(request);
        if (token == null
                || !TokenUtil.verify(token)
                || redisService.getTokenInRedis(userId) == null
                || !redisService.getTokenInRedis(userId).equals(token)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = new JSONObject();
            json.put("msg", "token verify fail");
            json.put("code", "50000");
            response.getWriter().append(json.toJSONString());
            System.out.println("认证失败，未通过拦截器");
            return false;
        }

        //离过期还有30分钟
        if (redisService.getTokenExpireTime(userId) < 30 * 60) {
            redisService.setTokenInRedis(userId, token);
        }
        return true;
    }
}
