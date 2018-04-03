package com.sinosoft.stukpisys.servsce;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

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
    Map<String,Integer> getPopulationBySexDiffer();
    List<Map<String,Integer>> getPopulationByEducationDiffer();
    List<String> getDifferEducateName();
    List<Map<String,Integer>> getPopulationByMajorDiffer();
    List<String> getDifferMajorName();
    Map<String,Integer>  getPopulationByIs211();
    List<Map<String,Integer>> getPopulationByLocationDiffer();
    List<String> getDifferLocationName();
//    List<List<Object>> getUserInfoByParam(String HRName,String job,String school,String education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211);
    //3-29 服务traineeController
    Education getEduInfoByUserName(String userName);
    List<List<Object>> getScoreFromStageByUser_id(int userId,int stage);
    List<ScoreValue>  getJudgeByParam(String HRName,String job,String school,String Education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211);
    List<ScoreValue> getUserScoreParam(String HRName,String job,String school,String Education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211);
    User getByName(String name);


    List<UserInfo> gethrName();
    List<UserInfo> getJob();
    List<Education> getschoolName();
    List<Education> getHighestEducate();
    List<Education> getMajor();




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

    /**
     * 按2或3阶段查看在后5的人员的id
     * @param stage
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Long> getScoreInbackByStage2(int stage);
    /**
     * 根据id查成绩,返回姓名和各个阶段的成绩
     * @param id
     * @return
     */
    /**
     * 按阶段查看在后5的人员的id
     * @param stage
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Long> getScoreInbackByStage(int stage);
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<Object> getScoreById(long id);

    /**
     * 按阶段数查看成绩都在后5的人员的成绩
     * @param stages
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<ScoreValue> getScoreInback(int stages);
    /**
     * 通过id查三个阶段评价
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    List<List<Object>> getJudgeInback(long id);

}
