package com.example.experiment06validation.controller;

import com.example.experiment06validation.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated          // 声明使用方法级校验
public class UserController {
    @PostMapping("/users")
    public Map postUser(@Valid @RequestBody User user) {
        return Map.of("user", user);
    }
    // 方法级校验
    @GetMapping("/users/{username}")
    public void getViolationException(
            @Size(min = 2, max = 6, message = "用户参数信息错误")
            @PathVariable String username) {
    }
}
