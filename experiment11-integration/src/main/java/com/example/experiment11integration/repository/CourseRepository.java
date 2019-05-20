package com.example.experiment11integration.repository;

import com.example.experiment11integration.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CustomizedRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.teacher.id=:tid")
    List<Course> list(@Param("tid") int tid);

    @Query("FROM Course c WHERE c.id=:cid AND c.teacher.id=:tid")
    Course find(@Param("cid") int cid, @Param("tid") int tid);
}
