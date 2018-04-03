package com.sinosoft.stukpisys.servsce;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * 用来更改用户信息
 */
public interface AdminService {

    int changeJudgeByUserNameAndLabelName(@Param("userName")String  userName,@Param("labelName") String labelName,@Param("judge") String judge);

   int setDept(@Param("userName")String userName,@Param("dept")String dept);

    int changeInfoByUsernameAndKeyValue(@Param("state")String  state, @Param("hrName")String  hrName, @Param("job")String  job, @Param("gender")long  gender, @Param("email")String  email, @Param("birth")Date birth, @Param("nativePlace")String  nativePlace, @Param("phone")String  phone, @Param("enterTime")Date  enterTime, @Param("eduId")long  eduId, @Param("dept")String  dept, @Param("userName")String  userName);


}
