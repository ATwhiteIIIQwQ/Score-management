package com.atotwhite.service;

import com.atotwhite.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> showAllCourse();
    String addCourse(Course course);
    String updateCourse(int courseId, Course course);
    String deleteCourse(int courseId);
    List<Course> searchCourse(Course course);
}
