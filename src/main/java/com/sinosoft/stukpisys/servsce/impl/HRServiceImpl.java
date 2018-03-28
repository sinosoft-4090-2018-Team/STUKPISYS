package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HRServiceImpl implements HRService{

    @Autowired
    private UserDao userDao;

    public List<UserInfo> getAllInfo() {
        return userDao.getAllInfo();
    }

    public List<Education> getEduInfo() {
        return userDao.getEduInfo();
    }

    @Override
    public UserInfo getPersonInfoByName(String name) {
        return userDao.getPersonInfoByName(name);
    }

    @Override
    public List<UserInfo> getInfoByState(String state) {
        return userDao.getInfoByState(state);
    }

    @Override
    public int distPerson(String name, String dept) {
        int count=userDao.distPerson(name,dept);
        return count;

    }

    @Override
    public List<UserInfo> getUserIdByhrName(String hr) {
        return userDao.getUserIdByhrName(hr);
    }

    @Override
    public List<Integer> getScoreList(Integer id) {
        return userDao.getScoreList(id);
    }
}
