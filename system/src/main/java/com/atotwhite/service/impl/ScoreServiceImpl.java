package com.atotwhite.service.impl;

import com.atotwhite.domain.Score;
import com.atotwhite.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Score> showAllScores() {
        //查看全部信息
        String sql = "SELECT * FROM oop_score";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Score(
                        rs.getInt("score_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getInt("score")
                )
        );
    }

    @Override
    public String addScore(Score score) {
        // 添加信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO oop_score SET ");

        if (score.getStudentId() != null) {
            sql.append("student_id = ?, ");
            params.add(score.getStudentId());
        }
        if (score.getCourseId() != null) {
            sql.append("course_id = ?, ");
            params.add(score.getCourseId());
        }
        if (score.getScore() != null) {
            sql.append("score = ?, ");
            params.add(score.getScore());
        }

        if (params.isEmpty()) {
            return "无有效数据";
        }

        sql.delete(sql.length() - 2, sql.length());
        
        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public String updateScore(Integer scoreId, Score score) {
        //修改信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE oop_score SET ");

        if (score.getStudentId() != null) {
            sql.append("course_name = ?, ");
            params.add(score.getStudentId());
        }
        if (score.getScoreId() != null) {
            sql.append("course_credit = ?, ");
            params.add(score.getScoreId());
        }
        if (score.getScore() != null) {
            sql.append("score = ?, ");
            params.add(score.getScore());
        }

        if (params.isEmpty()) {
            return "未提供修改内容";
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE score_id = ?");
        params.add(scoreId);

        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public String deleteScore(Integer scoreId) {
        //删除信息操作
        String sql = "DELETE FROM oop_score WHERE score_id = ?";
        int rows = jdbcTemplate.update(sql, scoreId);
        return rows > 0 ? "success" : "fail";
    }

    @Override
    public List<Score> searchScore(Score score) {
        //查询信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM oop_course WHERE 1=1 ");

        if (score.getStudentId() != null) {
            sql.append("AND student_id = ? ");
            params.add(score.getStudentId());
        }
        if (score.getCourseId() != null) {
            sql.append("AND course_id = ? ");
            params.add(score.getCourseId());
        }
        if (score.getScore() != null) {
            sql.append("AND score = ? ");
            params.add(score.getScore());
        }

        sql.delete(sql.length() - 1, sql.length());

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new Score(
                        rs.getInt("score_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getInt("score")
                )
        );
    }
}

