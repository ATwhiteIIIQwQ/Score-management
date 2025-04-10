package com.atotwhite.service.impl;

import com.atotwhite.domain.Course;
import com.atotwhite.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
    public Page<Course> getCourseByPage(int page, int size) {
        int offset = (page - 1) * size;
        String sql = String.format("SELECT * FROM oop_course LIMIT %d OFFSET %d", size, offset);
        String count = "SELECT COUNT(*) FROM oop_course";
        
        List<Course> list = jdbcTemplate.query(sql, (rs, rowNum) ->
            new Course(
                rs.getInt("course_id"),
                rs.getString("course_name"),
                rs.getInt("course_credit")
            )
        );
        
        int total = jdbcTemplate.queryForObject(count, Integer.class);
        
        return new PageImpl<>(list, PageRequest.of(page-1, size), total);
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
        if (course.getCourseCredit() != null) {
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
    public String updateCourse(Integer courseId, Course course) {
        //修改信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE oop_course SET ");

        if (course.getCourseName() != null) {
            sql.append("course_name = ?, ");
            params.add(course.getCourseName());
        }
        if (course.getCourseCredit() != null) {
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
    public String deleteCourse(Integer courseId) {
        //删除信息操作
        String sql = "DELETE FROM oop_course WHERE course_id = ?";
        int rows = jdbcTemplate.update(sql, courseId);
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public Page<Course> searchCourse(int page, int size, String courseName, Integer courseCredit) {
        int offset = (page - 1) * size;
        List<Object> params = new ArrayList<>();
        
        StringBuilder querySql = new StringBuilder("SELECT * FROM oop_course WHERE 1=1 ");
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM oop_course WHERE 1=1 ");
    
        if (courseName != null && !courseName.isEmpty()) {
            querySql.append("AND course_name LIKE ? ");
            countSql.append("AND course_name LIKE ? ");
            params.add("%" + courseName + "%");
        }
        if (courseCredit != null) {
            querySql.append("AND course_credit = ? ");
            countSql.append("AND course_credit = ? ");
            params.add(courseCredit);
        }
    
        querySql.append(String.format(" LIMIT %d OFFSET %d", size, offset));
    
        List<Course> list = jdbcTemplate.query(querySql.toString(), params.toArray(), (rs, rowNum) ->
            new Course(
                rs.getInt("course_id"),
                rs.getString("course_name"),
                rs.getInt("course_credit")
            )
        );
    
        int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
        
        return new PageImpl<>(list, PageRequest.of(page-1, size), total);
    }
}
