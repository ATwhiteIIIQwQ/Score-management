package com.atotwhite.service;

import com.atotwhite.domain.User;

import java.util.List;

public interface UserService {
    List<User> showAllUsers();
    String addUser(User user);
    String updateUser(int userId, User user);
    String deleteUser(int userId);
    List<User> searchUser(User user);
}
