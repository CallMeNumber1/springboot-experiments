package com.example.experiment07interceptor.controller;

import com.example.experiment07interceptor.component.EncryptorComponent;
import com.example.experiment07interceptor.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private Map<String, User> users = new HashMap();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;
    @PostMapping("/register")
    public Map register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.put(user.getUsername(), user);
        return Map.of("user", user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(users.get(user.getUsername()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名不存在!");
                })
                .ifPresent(u -> {
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");
                    }
                    Map map = Map.of("name", u.getUsername());
                    String token = encryptorComponent.encrypt(map);
                    // response.header -> request.header 如何实现的
                    response.setHeader("Authorization", token);
                });
    }
    // 此处参数名称是否要和属性名相同
    @GetMapping("/index")
    public Map getIndex(@RequestAttribute String name) {
        log.debug(name);
        return Map.of("username", name);
    }
}
