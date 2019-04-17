package com.example.backendintegration.interceptor;

import com.example.backendintegration.componet.EncryptorComponet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


// 创建拦截器组件，判断header中token是否合法，将反序列化出的用户信息，加载到当前request
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private EncryptorComponet encryptorComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.debug("------interceptor----------");
        System.out.println("--------interceptor------------");
        Optional.ofNullable(request.getHeader("Authorization"))
                .ifPresentOrElse(token -> {
                    var map = encryptorComponent.decrypt(token);
                    request.setAttribute("uid", map.get("uid"));
                    request.setAttribute("aid", map.get("aid"));
                }, () -> {
                    log.debug("------error------");
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录!");
                });
        return true;
    }
}
