package com.atotwhite.service;

import com.atotwhite.domain.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    Page<Student> getStudentByPage(int page, int size);
    String addStudent(Student student);
    String updateStudent(Integer studentId, Student student);
    String deleteStudent(Integer studentId);
    Page<Student> searchStudent(int page, int size, String studentNum, String studentName, String studentGrade, String studentClass);
}
