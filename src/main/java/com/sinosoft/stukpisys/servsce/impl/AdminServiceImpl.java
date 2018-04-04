package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.servsce.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    InfoDao infoDao;
    @Autowired
    ScoreDao scoreDao;


    @Override
    public int changeJudgeByUserNameAndLabelName(String userName,String labelName, String judge) {
      User user= scoreDao.getUserIdByUserName(userName);
      ScoreLabel scoreLabel=scoreDao.getLabelIndexByLabelName(labelName);
      if(user!=null&&scoreLabel!=null){
          long userId= user.getUserId();
          long labelIndex=scoreLabel.getLabelIndex();
          int count=scoreDao.changeJudgeByUserNameAndLabelName((int)(userId),labelIndex,judge);
      //System.out.println(count);
          return  count;
      }else{
          return 0;
      }

    }

    @Override
    public int setDept(String userName, String dept) {
      //  long userId=scoreDao.getUserIdByUserName(userName).getUserId();
      int count= infoDao.setDept(userName,dept);
           return count;
    }

    @Override
    public int changeInfoByUsernameAndKeyValue(String state, String hrName, String job, long gender, String email, Date birth, String nativePlace, String phone, Date enterTime, long eduId, String dept, String userName) {
        scoreDao.changeInfoByUsernameAndKeyValue(state,  hrName, job, gender, email, birth, nativePlace, phone, enterTime, eduId, dept, userName);
            return 0;
    }
}
