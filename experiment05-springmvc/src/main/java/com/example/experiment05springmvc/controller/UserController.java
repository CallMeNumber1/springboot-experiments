package com.example.experiment05springmvc.controller;

import com.example.experiment05springmvc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    // 类体里面不能执行add方法（类的方法里才能写这样的语句）
    private static List<User> users = create();
    private static List<User> create() {
        users = new ArrayList<>();
        User u1 = new User(1, "BO", "123");
        User u2 = new User(2, "Chong", "456");
        users.add(u1);
        users.add(u2);
        return users;
    }
    @GetMapping("/user")
    public Map getUsers() {
        return Map.of("users", users);
    }
    @GetMapping("/users/{uid}")
    public Map getUser(@PathVariable int uid) {
        log.debug("{}", uid);
        User user = users.stream()
                .filter(u -> u.getId() == uid)
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(user)
                .map(u -> Map.of("user", u))
                .orElse(Map.of());
        // 为何下面这两句报错
//        return Optional.ofNullable(user)
//                .ifPresentOrElse(u -> Map.of("user", u), Map.of());
//        for (User u : users) {
//            if (u.getId() == uid) {
//                return Map.of("user", u);
//            }
//        }
//        return Map.of();
    }
    @PostMapping("/users")
    public Map addUser(@RequestBody User user) {
        users.add(user);
        return Map.of("users", users);
    }
    @GetMapping("/index")
    public Map getIndex() {
        return Map.of("name", "bo");
    }
}
