package com.atotwhite.service;

import com.atotwhite.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> showAllStudent();
    String addStudent(Student student);
    String updateStudent(int studentId, Student student);
    String deleteStudent(int studentId);
    List<Student> searchStudent(Student student);
}
