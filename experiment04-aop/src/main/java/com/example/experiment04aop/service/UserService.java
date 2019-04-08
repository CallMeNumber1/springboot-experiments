package com.example.experiment04aop.service;


import com.example.experiment04aop.aspect.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@MyInterceptor
@Slf4j
public class UserService {
    public void buyCar() {}
    @MyInterceptor(value = {})
    public void addUser() {
        log.debug("管理员权限");
    }
    public void getUser() {
        log.debug("普通用户权限");
    }
}
