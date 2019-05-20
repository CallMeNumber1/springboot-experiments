package com.example.experiment11integration.service;

import com.example.experiment11integration.entity.User;
import com.example.experiment11integration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// 初始化一个超级管理员
@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setAuthority(User.ADMIN_AUTHORITY);
            user.setNumber("1001");
            user.setPassword(passwordEncoder.encode(user.getNumber()));
            user.setName("CHONG");
            userRepository.save(user);
        }
    }
}
