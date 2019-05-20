package com.example.experiment11integration.service;

import com.example.experiment11integration.entity.Course;
import com.example.experiment11integration.repository.CourseDetailRepository;
import com.example.experiment11integration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseDetailRepository courseDetailRepository;
    /**
     * 获取指定教师的所有课程
     * @param tid
     * @return
     */
    public List<Course> listTeacherCourses(int tid) {
        return courseRepository.list(tid);
    }
    /**
     * 获取指定学生的所有课程
     * @param sid
     * @return
     */
    public List<Course> listStudentCourses(int sid) {
        return courseDetailRepository.list(sid);
    }
    public Course addCourse(Course course) {
        courseRepository.save(course);
        return courseRepository.refresh(course);
    }
    public Course getTeacherCourse(int cid, int tid) {
        return courseRepository.find(cid, tid);
    }
    public Course getStudentCourse(int cid, int sid) {
        return courseDetailRepository.find(cid, sid);
    }
}
