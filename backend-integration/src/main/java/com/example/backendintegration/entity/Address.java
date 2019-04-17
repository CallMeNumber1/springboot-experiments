package com.example.backendintegration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.loadtime.definition.Definition;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String detail;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(columnDefinition = "timestamp not null default current_timestamp", updatable = false, insertable = false)
    private LocalDateTime insertTime;
}
