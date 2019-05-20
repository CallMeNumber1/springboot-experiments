package com.example.experiment11integration.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
// 一个老师对应多个课程，一个课程又对应多个作业
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    // 内容，普通字符串可能长度不够
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLineTime;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    public Homework(int id) {
        this.id = id;
    }
}
