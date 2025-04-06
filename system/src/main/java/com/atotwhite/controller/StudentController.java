package com.atotwhite.controller;

import com.atotwhite.domain.Student;
import com.atotwhite.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

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
    public List<Student> search(@ModelAttribute Student student) {
        return studentService.searchStudent(student);
    }
}
