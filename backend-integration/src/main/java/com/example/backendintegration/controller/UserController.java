package com.example.backendintegration.controller;

import com.example.backendintegration.componet.EncryptorComponet;
import com.example.backendintegration.entity.Address;
import com.example.backendintegration.entity.User;
import com.example.backendintegration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponet encryptorComponet;
//    @GetMapping("/test")
//    public Map getTest() {
//        return Map.of("user", "aaa");
//    }
    @PostMapping("/register")
    public Map register(@RequestBody User user) {
        log.debug("-----------enter--------");
        System.out.println("----------enter-----------");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return Map.of("user", user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(userService.getUser(user.getUsername()))
                .ifPresentOrElse(u -> {
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误！");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAid());
                    // 生成加密token
                    String token = encryptorComponet.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("Authorization", token);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户不存在！");
                });
    }
    // 通过注解可以直接将request身上的属性拿到
    @PostMapping("/users/{uid}/addresses")
    public Map postAddress(@RequestBody Address address, @RequestAttribute int uid) {
        address.setUser(new User(uid));
        userService.addAddress(address);
        return Map.of("address", address);
    }
    @GetMapping("/users/{uid}/addresses")
    public Map getAddresses(@RequestAttribute int uid) {
        // 这里为何不用optional，万一为空呢？
        return Map.of("addresses", userService.listAddresses(uid));
    }
    @PatchMapping("/users/{uid}/addresses/{aid}")
    public Map patchAddress(@RequestBody Address address) {
        return Map.of("address", userService.updateAddress(address));
    }
}
