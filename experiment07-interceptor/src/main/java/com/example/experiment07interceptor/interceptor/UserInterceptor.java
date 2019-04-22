package com.example.experiment07interceptor.interceptor;

import com.example.experiment07interceptor.component.EncryptorComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private EncryptorComponent encryptorComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // ifPresentOrElse 和 ifPresent . or 用法一样吗
        Optional.ofNullable(request.getHeader("Authorization"))
                .ifPresentOrElse(token -> {
                    var map = encryptorComponent.decrypt(token);
                    request.setAttribute("name", map.get("name"));
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
                });
        return true;
    }
}
