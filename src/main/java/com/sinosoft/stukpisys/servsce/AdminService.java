package com.sinosoft.stukpisys.servsce;

/**
 * 用来更改用户信息
 */
public interface AdminService {

    int changeJudgeByUserNameAndLabelName(String userName,String labelName,String judge);

   int setDept(String userName,String dept);

}
