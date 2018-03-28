package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * 专门用来查询用户信息
 */
public interface HRService {



    //按名字获取一个人的详细信息
    UserInfo getPersonInfoByName(String name);
    //按实习生状态查询实习生-转到admin
    List<UserInfo> getUserInfoByState(String state);
    // 通过hr查询实习生
    List<UserInfo> getUserIdByhrName(String hrName);
    List<UserInfo> getUserByJob(String job);
    List<UserInfo> getUserBySex(String sex);
    List<UserInfo> getUserByEnterTime(String  enterTime);
    List<UserInfo> getUserBySchool(String name);
    List<UserInfo> getUserByHighestEducate(String educate);







}
