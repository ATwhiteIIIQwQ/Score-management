package com.atotwhite.controller;

import com.atotwhite.domain.Course;
import com.atotwhite.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/list")
    public List<Course> list() {
        return courseService.showAllCourse();
    }

    @GetMapping("/page")
    public Page<Course> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return courseService.getCourseByPage(pageNum, pageSize);
    }

    @PostMapping
    public String add(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/search")
    public List<Course> search(@ModelAttribute Course course) {
        return courseService.searchCourse(course);
    }
}
