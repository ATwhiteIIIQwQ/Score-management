package com.atotwhite.service.impl;

import com.atotwhite.domain.Score;
import com.atotwhite.service.ScoreService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addScore(Score score) {
        //添加信息操作
        String sql = "insert into student_manage values(?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                score.getId(),
                score.getName(),
                score.getAge(),
                score.getAddress(),
                score.getScore()
        );
        return rows > 0 ? "success" : "fail";
    }

    public List<Score> showAllScore() {
        //查看全部学生信息
        String sql = "select * from student_manage";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Score(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("address"),
                    rs.getInt("score")
            )
        );
    }

    public String updateScore(int id, Score score) {
        //修改数据操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE student_manage SET ");

        if (score.getName() != null) {
            sql.append("name = ?, ");
            params.add(score.getName());
        }
        if (score.getAge() != 0) {
            sql.append("age = ?, ");
            params.add(score.getAge());
        }
        if (score.getAddress() != null) {
            sql.append("address = ?, ");
            params.add(score.getAddress());
        }

        if (params.isEmpty()) {
            return "未提供修改内容";
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE id = ?");
        params.add(id);

        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    public String deleteScore(int id) {
        //删除数据操作
        String sql = "delete from student_manage where id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    public List<Score> searchScore(Score score) {
        //查询信息操作
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from student_manage where 1=1 ");

        if (score.getId() != 0) {
            sql.append("AND id = ? ");
            params.add(score.getId());
        }
        if (score.getName() != null) {
            sql.append("AND name LIKE ? ");
            params.add("%" + score.getName() + "% ");
        }
        if (score.getAge() != 0) {
            sql.append("AND age = ? ");
            params.add(score.getAge());
        }
        if (score.getAddress() != null) {
            sql.append("AND address LIKE ? ");
            params.add("%" + score.getAddress() + "% ");
        }

        sql.delete(sql.length() - 1, sql.length());

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) ->
            new Score(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("address"),
            rs.getInt("score")
            ), params.toArray()
            );
    }
}

