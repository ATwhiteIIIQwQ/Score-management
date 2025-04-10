package com.atotwhite.controller;

import com.atotwhite.domain.Student;
import com.atotwhite.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.getAllStudent();
    }

    @GetMapping("/page")
    public Page<Student> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return studentService.getStudentByPage(pageNum, pageSize);
    }

    @PostMapping
    public String add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/search")
    public Page<Student> search(
        @RequestParam(defaultValue = "1") Integer pageNum,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) String studentNum,
        @RequestParam(required = false) String studentName,
        @RequestParam(required = false) String studentGrade,
        @RequestParam(required = false) String studentClass) {
        return studentService.searchStudent(pageNum, pageSize, studentNum, studentName, studentGrade, studentClass);
    }
}
