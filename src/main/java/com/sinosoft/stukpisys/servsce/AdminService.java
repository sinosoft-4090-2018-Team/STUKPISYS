package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.UserInfo;

import java.util.List;

public interface AdminService {
    List<UserInfo> getUserByJob(String job);
    List<UserInfo> getUserBySex(String sex);
    List<UserInfo> getUserByEnterTime(String  enterTime);
}
