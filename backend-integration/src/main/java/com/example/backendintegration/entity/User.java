package com.example.backendintegration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    // 禁止User中密码属性序列化
    @JsonProperty(access =  JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int aid = 1;
//    @OneToMany(mappedBy = "user")
//    private List<Address> addresses;
    @Column(columnDefinition = "timestamp not null default current_timestamp",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
    public User(int id) {
        this.id = id;
    }
}
