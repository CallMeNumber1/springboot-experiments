package com.example.experiment04aop;

import com.example.experiment04aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void buyCarsTest() {
        userService.buyCar();
    }
    @Test
    public void addUserTest() {
        userService.addUser();
    }
    @Test
    public void getUserTest() {
        userService.getUser();
    }
}
