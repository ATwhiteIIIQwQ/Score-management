package com.atotwhite.service.impl;

import com.atotwhite.domain.Student;
import com.atotwhite.service.StudentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> showAllStudent() {
        //查看全部信息
        String sql = "SELECT * FROM oop_student";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_num"),
                        rs.getString("student_name"),
                        rs.getInt("student_grade"),
                        rs.getString("student_class")
                )
        );
    }

    @Override
    public String addStudent(Student student) {
        //添加信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO oop_student SET ");

        if (student.getStudentNum() != null) {
            sql.append("student_num = ?, ");
            params.add(student.getStudentNum());
        }
        if (student.getStudentName() != null) {
            sql.append("student_name = ?, ");
            params.add(student.getStudentName());
        }
        if (student.getStudentGrade() != null) {
            sql.append("student_grade = ?, ");
            params.add(student.getStudentGrade());
        }
        if (student.getStudentClass() != null) {
            sql.append("student_class = ?, ");
            params.add(student.getStudentClass());
        }
    
        if (params.isEmpty()) {
            return "无有效数据";
        }
    
        sql.delete(sql.length() - 2, sql.length());
        
        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public String updateStudent(Integer studentId, Student student) {
        //修改信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE oop_student SET ");

        if (student.getStudentNum() != null) {
            sql.append("student_num = ?, ");
            params.add(student.getStudentNum());
        }
        if (student.getStudentName() != null) {
            sql.append("student_name = ?, ");
            params.add(student.getStudentName());
        }
        if (student.getStudentGrade() != null) {
            sql.append("student_grade = ?, ");
            params.add(student.getStudentGrade());
        }
        if (student.getStudentClass() != null) {
            sql.append("student_class = ?, ");
            params.add(student.getStudentClass());
        }

        if (params.isEmpty()) {
            return "未提供修改内容";
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE student_id = ?");
        params.add(studentId);

        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public String deleteStudent(Integer studentId) {
        //删除信息操作
        String sql = "DELETE FROM oop_student WHERE student_id = ?";
        int rows = jdbcTemplate.update(sql, studentId);
        return rows > 0 ? "删除成功" : "删除失败（ID不存在）";
    }

    @Override
    public List<Student> searchStudent(Student student) {
        //查询信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM oop_student WHERE 1=1 ");

        if (student.getStudentNum() != null) {
            sql.append("AND student_num = ? ");
            params.add(student.getStudentNum());
        }
        if (student.getStudentName() != null) {
            sql.append("AND student_name LIKE ? ");
            params.add("%" + student.getStudentName() + "%");  // 移除末尾多余的空格
        }
        if (student.getStudentGrade() != null) {
            sql.append("AND student_grade = ? ");
            params.add(student.getStudentGrade());
        }
        if (student.getStudentClass() != null) {
            sql.append("AND student_class LIKE ? ");
            params.add("%" + student.getStudentClass() + "%");  // 班级字段同样修正
        }

        sql.delete(sql.length() - 1, sql.length());

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
            new Student(
                rs.getInt("student_id"),
                rs.getString("student_num"),
                rs.getString("student_name"),
                rs.getInt("student_grade"),
                rs.getString("student_class")
            )
        );
    }
}
