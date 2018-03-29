package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HRServiceImpl implements HRService{

    @Autowired
    private InfoDao infoDao;

    @Override
    public UserInfo getPersonInfoByName(String name) {
        return infoDao.getPersonInfoByName(name);
    }

    @Override
    public List<UserInfo> getUserInfoByState(String state) {
        return infoDao.getInfoByState(state);
    }

    @Override
    public List<UserInfo> getUserIdByhrName(String hrName) {
        return infoDao.getUserIdByhrName(hrName);
    }


    @Override
    public List<UserInfo> getUserByJob(String job) {
        return infoDao.getUserByJob(job);
    }

    @Override
    public List<UserInfo> getUserBySex(String sex) {

        return infoDao.getUserBySex(sex);
    }

    @Override
    public List<UserInfo> getUserByEnterTime(String enterTime) {
        return infoDao.getUserByEnterTime(enterTime);
    }

    @Override
    public List<UserInfo> getUserBySchool(String name) {
        return infoDao.getUserBySchoolName(name);
    }

    @Override
    public List<List<Object>> getUserScoreByStageAndTime(int stage, Date enter_time) {
//        return scoreDao.getUserScoreByStageAndTime(stage,enter_time);
        return null;
    }

    @Override
    public List<UserInfo> getUserByHighestEducate(String educate) {
        return infoDao.getUserByHighestEducate(educate);
    }

    @Override
    public int getPopulationBySexDiffer(int gender) {
        return  infoDao.getPopulationBySexDiffer(gender);
    }

    @Override
    public int getPopulationByEducationDiffer(String education) {
        return infoDao.getPopulationByEducationDiffer(education);
    }

    @Override
    public List<String> getDifferEducateName() {
        return infoDao.getDifferEducateName();
    }

    @Override
    public int getPopulationByMajorDiffer(String major) {
        return infoDao.getPopulationByMajorDiffer(major);
    }

    @Override
    public List<String> getDifferMajorName() {
        return infoDao.getDifferMajorName();
    }

    @Override
    public int getPopulationByIs211(int is211) {
        return infoDao.getPopulationByIs211(is211);
    }

    @Override
    public int getPopulationByLocationDiffer(String location) {
        return infoDao.getPopulationByLocationDiffer(location);
    }

    @Override
    public List<String> getDifferLocationName() {
        return infoDao.getDifferLocationName();
    }

    //未知方法
    @Override
    public List<Integer> getScoreList(Integer id) {
        return null;
    }


}
