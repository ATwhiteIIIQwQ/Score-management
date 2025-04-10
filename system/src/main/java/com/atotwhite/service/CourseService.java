package com.atotwhite.service;

import com.atotwhite.domain.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<Course> showAllCourse();
    Page<Course> getCourseByPage(int page, int size);
    String addCourse(Course course);
    String updateCourse(Integer courseId, Course course);
    String deleteCourse(Integer courseId);
    Page<Course> searchCourse(int page, int size, String courseName, Integer courseCredit);
}
