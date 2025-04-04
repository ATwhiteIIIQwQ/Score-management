package com.atotwhite.service;

import com.atotwhite.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> showAllCourse();
    String addCourse(Course course);
    String updateCourse(Integer courseId, Course course);
    String deleteCourse(Integer courseId);
    List<Course> searchCourse(Course course);
}
