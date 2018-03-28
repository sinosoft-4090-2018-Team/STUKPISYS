package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    UserDao userDao;
    @Override
    public List<UserInfo> getUserByJob(String job) {
        return userDao.getUserByJob(job);
    }

    @Override
    public List<UserInfo> getUserBySex(String sex) {
        return userDao.getUserBySex(sex);
    }

    @Override
    public List<UserInfo> getUserByEnterTime(String enterTime) {
        return userDao.getUserByEnterTime(enterTime);
    }
}
