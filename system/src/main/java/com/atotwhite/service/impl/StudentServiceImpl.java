package com.atotwhite.service.impl;

import com.atotwhite.domain.Student;
import com.atotwhite.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> getAllStudent() {
        //查看全部信息
        String sql = "SELECT * FROM oop_student";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Student(
                rs.getInt("student_id"),
                rs.getString("student_num"),
                rs.getString("student_name"),
                rs.getString("student_grade"),
                rs.getString("student_class")
            )
        );
    }

    @Override
    public Page<Student> getStudentByPage(int page, int size) {
        int offset = (page - 1) * size;
        String sql = String.format("SELECT * FROM oop_student LIMIT %d OFFSET %d", size, offset);
        String count = "SELECT COUNT(*) FROM oop_student";
        
        List<Student> list = jdbcTemplate.query(sql, (rs, rowNum) ->
            new Student(
                rs.getInt("student_id"),
                rs.getString("student_num"),
                rs.getString("student_name"),
                rs.getString("student_grade"),
                rs.getString("student_class")
            )
        );
        
        int total = jdbcTemplate.queryForObject(count, Integer.class);
        
        return new PageImpl<>(list, PageRequest.of(page-1, size), total);
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
    public Page<Student> searchStudent(int page, int size, String studentNum, String studentName, String studentGrade, String studentClass) {
        int offset = (page - 1) * size;
        List<Object> params = new ArrayList<>();
        
        StringBuilder querySql = new StringBuilder("SELECT * FROM oop_student WHERE 1=1 ");
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM oop_student WHERE 1=1 ");
    
        if (studentNum != null && !studentNum.isEmpty()) {
            querySql.append("AND student_num LIKE ? ");
            countSql.append("AND student_num LIKE ? ");
            params.add("%" + studentNum + "%");
        }
        if (studentName != null &&!studentName.isEmpty()) {
            querySql.append("AND student_name LIKE ? ");
            countSql.append("AND student_name LIKE ? ");
            params.add("%" + studentName + "%");
        }
        if (studentGrade != null &&!studentGrade.isEmpty()) {
            querySql.append("AND student_grade LIKE ? ");
            countSql.append("AND student_grade LIKE ? ");
            params.add("%" + studentGrade + "%");
        }
        if (studentClass != null &&!studentClass.isEmpty()) {
            querySql.append("AND student_class LIKE ? ");
            countSql.append("AND student_class LIKE ? ");
            params.add("%" + studentClass + "%");
        }
    
        querySql.append(String.format(" LIMIT %d OFFSET %d", size, offset));
    
        List<Student> list = jdbcTemplate.query(querySql.toString(), params.toArray(), (rs, rowNum) ->
            new Student(
                rs.getInt("student_id"),
                rs.getString("student_num"),
                rs.getString("student_name"),
                rs.getString("student_grade"),
                rs.getString("student_class")
            )
        );
    
        int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
        
        return new PageImpl<>(list, PageRequest.of(page-1, size), total);
    }
}
