package com.example.experiment11integration.interceptor;

import com.example.experiment11integration.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 教师级请求权限验证拦截器
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 先经过login拦截器，如果未登录不会进入
         * login拦截器已将权限id注入request attribute，因此可直接取出判断
         */
        int aid = (int)request.getAttribute("aid");
        if (aid != User.ADMIN_AUTHORITY) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
        return true;
    }
}
