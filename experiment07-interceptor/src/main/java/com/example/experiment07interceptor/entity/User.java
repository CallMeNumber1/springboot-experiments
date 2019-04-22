package com.example.experiment07interceptor.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String username;
    // 属性序列化将被忽略，反序列化无影响
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
