package com.example.experiment11integration.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HomeworkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String solution;
    @ManyToOne(fetch = FetchType.LAZY)
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    private Homework homework;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime completeTime;
}
