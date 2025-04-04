package com.atotwhite.service;

import com.atotwhite.domain.User;

import java.util.List;

public interface UserService {
    List<User> showAllUsers();
    String addUser(User user);
    String updateUser(Integer userId, User user);
    String deleteUser(Integer userId);
    List<User> searchUser(User user);
}
