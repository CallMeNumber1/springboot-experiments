package com.example.experiment06validation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Size(min = 2, max = 6, message = "您输入的值为${validatedValue},用户名长度必须大于{min},小于{max}")
    private String name;
    @Size(min = 6, message = "您的密码长度必须大于{value}")
    private String password;
    @Min(value = 18, message = "您的年龄必须大于{value}")
    @Max(value = 60, message = "您的年龄必须小于{value}")
    private int age;
    @Email(message = "您的Email必须合法")
    private String email;
}
