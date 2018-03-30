package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.servsce.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    InfoDao infoDao;
    @Autowired
    ScoreDao scoreDao;


    @Override
    public int changeJudgeByUserNameAndLabelName(String userName, String labelName, String judge) {
        long userId=scoreDao.getUserIdByUserName(userName).getUserId();
        scoreDao.changeJudgeByUserNameAndLabelName((int)userId,labelName,judge);
        return 0;
    }
}
