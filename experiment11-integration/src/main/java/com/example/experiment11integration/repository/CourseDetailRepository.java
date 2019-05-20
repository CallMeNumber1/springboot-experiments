package com.example.experiment11integration.repository;

import com.example.experiment11integration.entity.Course;
import com.example.experiment11integration.entity.CourseDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailRepository extends CustomizedRepository<CourseDetail, Integer> {
    /**
     * 获取指定学生的全部课程
     * @param sid
     * @return
     */
    @Query("SELECT cd.course FROM CourseDetail cd WHERE cd.student.id=:sid")
    List<Course> list(@Param("sid") int sid);
    @Query("FROM CourseDetail cd WHERE cd.course.id=:cid AND cd.student.id=:sid")
    Course find(@Param("cid") int cid, @Param("sid") int sid);
}
