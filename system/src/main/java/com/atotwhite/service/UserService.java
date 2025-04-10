package com.atotwhite.service;

import com.atotwhite.domain.User;
import com.atotwhite.domain.Result;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Result login(User user);
    List<User> showAllUsers();
    Page<User> getUserByPage(int page, int size);
    String addUser(User user);
    String updateUser(Integer userId, User user);
    String deleteUser(Integer userId);
    Page<User> searchUser(int page, int size, String userName, String userRole);
}
