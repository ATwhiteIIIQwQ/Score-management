package com.atotwhite.service.impl;

import com.atotwhite.domain.Course;
import com.atotwhite.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> showAllCourse() {
        //查看全部信息
        String sql = "SELECT * FROM oop_course";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("course_credit")
                )
        );
    }

    @Override
    public String addCourse(Course course) {
        // 添加信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO oop_course SET ");

        if (course.getCourseName() != null) {
            sql.append("course_name = ?, ");
            params.add(course.getCourseName());
        }
        if (course.getCourseCredit() != 0) {
            sql.append("course_credit = ?, ");
            params.add(course.getCourseCredit());
        }

        if (params.isEmpty()) {
            return "无有效数据";
        }

        sql.delete(sql.length() - 2, sql.length());
        
        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public String updateCourse(int courseId, Course course) {
        //修改信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE oop_course SET ");

        if (course.getCourseName() != null) {
            sql.append("course_name = ?, ");
            params.add(course.getCourseName());
        }
        if (course.getCourseCredit() != 0) {
            sql.append("course_credit = ?, ");
            params.add(course.getCourseCredit());
        }

        if (params.isEmpty()) {
            return "未提供修改内容";
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE course_id = ?");
        params.add(courseId);

        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public String deleteCourse(int courseId) {
        //删除信息操作
        String sql = "DELETE FROM oop_course WHERE course_id = ?";
        int rows = jdbcTemplate.update(sql, courseId);
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public List<Course> searchCourse(Course course) {
        //查询信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM oop_course WHERE 1=1 ");

        if (course.getCourseId() != 0) {
            sql.append("AND course_id = ?");
            params.add(course.getCourseId());
        }
        if (course.getCourseName() != null) {
            sql.append("AND course_name LIKE ? ");
            params.add("%" + course.getCourseName() + "% ");
        }
        if (course.getCourseCredit() != 0) {
            sql.append("AND course_credit = ? ");
            params.add(course.getCourseCredit());
        }

        sql.delete(sql.length() - 1, sql.length());

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) ->
                new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("course_credit")
                )
        );
    }
}
