package com.atotwhite.service.impl;

import com.atotwhite.domain.User;
import com.atotwhite.domain.Result;
import com.atotwhite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result login(User user) {
        try {
            String sql = "SELECT * FROM oop_user WHERE user_name = ?";
            User dbUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUserName());
            
            if (dbUser != null && dbUser.getUserPassword().equals(user.getUserPassword())) {
                return new Result(200, "登录成功", dbUser);
            }
            return new Result(401, "用户名或密码错误", null);
        } catch (EmptyResultDataAccessException e) {
            return new Result(404, "用户不存在", null);
        }
    }

    @Override
    public List<User> showAllUsers() {
        return jdbcTemplate.query("SELECT * FROM oop_user", (rs, rowNum) ->
            new User(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_password"),
                rs.getString("user_role")
            )
        );
    }

    @Override
    public Page<User> getUserByPage(int page, int size) {
        int offset = (page - 1) * size;
        String sql = String.format("SELECT * FROM oop_user LIMIT %d OFFSET %d", size, offset);
        String count = "SELECT COUNT(*) FROM oop_user";
        
        List<User> list = jdbcTemplate.query(sql, (rs, rowNum) ->
            new User(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_password"),
                rs.getString("user_role")
                )
        );
        
        int total = jdbcTemplate.queryForObject(count, Integer.class);
        return new PageImpl<>(list, PageRequest.of(page-1, size), total);
    }

    @Override
    public String addUser(User user) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("INSERT INTO oop_user SET ");
        
        if (user.getUserName() != null) {
            sql.append("user_name = ?, ");
            params.add(user.getUserName());
        }
        if (user.getUserPassword()!= null) {
            sql.append("user_password =?, ");
            params.add(user.getUserPassword()); 
        }
        if (user.getUserRole() != null) {
            sql.append("user_role = ?, ");
            params.add(user.getUserRole());
        }
        
        if (params.isEmpty()) return "无有效数据";
        
        sql.delete(sql.length() - 2, sql.length());
        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "添加成功" : "添加失败";
    }

    @Override
    public String updateUser(Integer userId, User user) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("UPDATE oop_user SET ");
        
        if (user.getUserName() != null) {
            sql.append("user_name = ?, ");
            params.add(user.getUserName());
        }
        if (user.getUserRole() != null) {
            sql.append("user_role = ?, ");
            params.add(user.getUserRole());
        }
        
        if (params.isEmpty()) return "未提供修改内容";
        
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE user_id = ?");
        params.add(userId);
        
        int rows = jdbcTemplate.update(sql.toString(), params.toArray());
        return rows > 0 ? "修改成功" : "修改失败（ID不存在）";
    }

    @Override
    public String deleteUser(Integer userId) {
        String sql = "DELETE FROM oop_user WHERE user_id = ?";
        int rows = jdbcTemplate.update(sql, userId);
        return rows > 0 ? "删除成功" : "删除失败（ID不存在）";
    }

    @Override
    public List<User> searchUser(User user) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM oop_user WHERE 1=1 ");
        
        if (user.getUserName() != null) {
            sql.append("AND user_name LIKE ? ");
            params.add("%" + user.getUserName() + "%");
        }
        if (user.getUserRole() != null) {
            sql.append("AND user_role LIKE ? ");
            params.add("%" + user.getUserRole() + "%");
        }
        
        sql.delete(sql.length() - 1, sql.length());
        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
            new User(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_password"),
                rs.getString("user_role")
            )
        );
    }
}
