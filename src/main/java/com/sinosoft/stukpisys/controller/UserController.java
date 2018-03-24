package com.sinosoft.stukpisys.controller;

import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.servsce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        userService.insert(user);
        return "";
    }
}
