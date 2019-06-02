package com.example.reviewaop.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    public void buyCar() {
        log.debug("buyCar()");
    }
}
