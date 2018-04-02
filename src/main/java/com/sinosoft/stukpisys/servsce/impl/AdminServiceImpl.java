package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.entity.User;
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
      User user= scoreDao.getUserIdByUserName(userName);
      if(user!=null){
          long userId= user.getUserId();
          scoreDao.changeJudgeByUserNameAndLabelName(judge,(int)(userId),labelName);
      }
        return 0;
    }

    @Override
    public int setDept(String userName, String dept) {
        long userId=scoreDao.getUserIdByUserName(userName).getUserId();
        infoDao.setDept((int)userId,dept);
        return 0;
    }
}
