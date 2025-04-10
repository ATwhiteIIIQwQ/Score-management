package com.atotwhite.controller;

import com.atotwhite.domain.Result;
import com.atotwhite.domain.User;
import com.atotwhite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.showAllUsers();
    }

    @GetMapping("/page")
    public Page<User> page(@RequestParam(defaultValue = "1") Integer pageNum, 
                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.getUserByPage(pageNum, pageSize);
    }

    @PostMapping
    public String add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/search")
    public Page<User> search(
        @RequestParam(defaultValue = "1") Integer pageNum,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) String userName,
        @RequestParam(required = false) String userRole) {
        return userService.searchUser(pageNum, pageSize, userName, userRole);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }
}
