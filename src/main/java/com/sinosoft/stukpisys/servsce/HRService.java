package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 专门用来查询用户信息
 */
public interface HRService {



    //按名字获取一个人的详细信息
    UserInfo getPersonInfoByName(String name);
    //按实习生状态查询实习生-转到adm
    List<UserInfo> getUserInfoByState(String state);
    // 通过hr查询实习生
    List<UserInfo> getUserIdByhrName(String hrName);
    List<UserInfo> getUserByJob(String job);
    List<UserInfo> getUserBySex(String sex);
    List<UserInfo> getUserByEnterTime(String  enterTime);
    List<UserInfo> getUserBySchool(String name);
    List<UserInfo> getUserByHighestEducate(String educate);
    //3-29 服务statisticsController
    int getPopulationBySexDiffer(int gender);
    int getPopulationByEducationDiffer(String education);
    List<String> getDifferEducateName();
    int getPopulationByMajorDiffer(String major);
    List<String> getDifferMajorName();
    int getPopulationByIs211(int is211);
    int getPopulationByLocationDiffer(String location);
    List<String> getDifferLocationName();
//    List<List<Object>> getUserInfoByParam(String HRName,String job,String school,String education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211);
    //3-29 服务traineeController
    Education getEduInfoByUserName(String userName);
    List<List<Object>> getScoreFromStageByUser_id(long userId,int stage);
    List<List<Object>> getUserInfoByParam(@Param("HRName")String HRName, String job, String school, String education, String major, boolean sex, boolean isFired, boolean isNew, boolean hasErr, boolean is211);
    User getByName(String name);




    /**
     * 通过id查询label的index和数值
     * @param name
     * @param dept
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Integer> getScoreList(Integer id);

    /**
     * 按阶段和时间查看总分数（排序）
     * @param stage
     * @param enter_time
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<List<Object>> getUserScoreByStageAndTime(int stage, java.sql.Date enter_time);

}
