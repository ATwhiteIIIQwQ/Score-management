package com.atotwhite.service.impl;

import com.atotwhite.domain.Score;
import com.atotwhite.service.ScoreService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addScore(Score score) {
        //添加信息操作
        String sql = "insert into student_manage values(?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                score.getId(),
                score.getName(),
                score.getAge(),
                score.getAddress()
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
                rs.getString("address"))
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
        return rows > 0 ? "success" : "fail";
    }
//
//    public void searchScore() throws Exception {
//        //查询信息操作
//        Menu();
//        int choice = sc.nextInt();
//        int id = 0; String name = null; int age = 0; String address = null;
//        switch (choice) {
//            case 1:
//                System.out.print("请输入要查询的学生ID：");
//                id = sc.nextInt();
//                if (!isScoreExists(id)) {
//                    System.out.println("未查询到对应学生信息");
//                    return;
//                }
//                break;
//            case 2:
//                System.out.print("请输入要查询的学生姓名：");
//                name = sc.next();
//                break;
//            case 3:
//                System.out.print("请输入要查询的学生年龄：");
//                age = sc.nextInt();
//                if (age < 5 || age > 50) {
//                    System.out.println("年龄无效！");
//                    return;
//                }
//                break;
//            case 4:
//                System.out.print("请输入要查询的学生地址：");
//                address = sc.next();
//                break;
//            default:
//                System.out.println("无效的选择");
//                break;
//        }
//
//        // 动态生成SQL语句      //开桂了
//        StringBuilder sql = new StringBuilder("select * from student_manage where ");
//        List<Object> params = new ArrayList<>();
//        if (id != 0) {
//            sql.append("id = ?, ");
//            params.add(id);
//        }
//        if (name != null) {
//            sql.append("name = ?, ");
//            params.add(name);
//        }
//        if (age != 0) {
//            sql.append("age = ?, ");
//            params.add(age);
//        }
//        if (address != null) {
//            sql.append("address = ?, ");
//            params.add(address);
//        }
//
//        // 去掉最后一个逗号和空格
//        sql.delete(sql.length() - 2, sql.length());
//
//        // 如果没有输入任何查询内容
//        if (params.isEmpty()) {
//            System.out.println("未输入任何查询内容！");
//            return;
//        }
//
//        pstm = conn.prepareStatement(sql.toString());
//        for (int i = 0; i < params.size(); i++) {
//            pstm.setObject(i + 1, params.get(i));
//        }
//        ResultSet rs = pstm.executeQuery();
//        if (!rs.next()) {
//            System.out.println("未查询到对应学生信息");
//        } else {
//            do {
//                id = rs.getInt(1);
//                name = rs.getString(2);
//                age = rs.getInt(3);
//                address = rs.getString(4);
//                System.out.println("id:" + id + " 姓名：" + name + " 年龄：" + age + " 地址：" + address);
//            } while (rs.next());
//        }
//    }
//
//    // 检查学生ID是否存在
//    private boolean isScoreExists(int id) throws SQLException {
//        String sql = "SELECT id FROM student_manage WHERE id = ?";
//        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
//            pstm.setInt(1, id);
//            try (ResultSet rs = pstm.executeQuery()) {
//                return rs.next(); // 如果存在返回true，否则返回false
//            }
//        }
//    }
}
