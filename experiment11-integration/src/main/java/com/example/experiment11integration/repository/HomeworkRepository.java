package com.example.experiment11integration.repository;

import com.example.experiment11integration.entity.Homework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends CustomizedRepository<Homework, Integer> {
    @Query("SELECT h FROM Homework h WHERE h.course.id=:cid AND h.course.teacher.id=:tid")
    List<Homework> list(@Param("cid") int cid, @Param("tid") int tid);

    @Query("SELECT h FROM Homework h LEFT JOIN CourseDetail cd ON h.course.id=cd.course.id " +
            "WHERE h.course.id=:cid AND cd.student.id=:sid")
    List<Homework> listByStudent(@Param("cid") int cid, @Param("sid") int sid);
}
