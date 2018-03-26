package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.servsce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public String insert(User user) {
        user.setAdmin(2);
        user.setCreateTime(new Date());
        userDao.insert(user);
        return "success";
    }

}
