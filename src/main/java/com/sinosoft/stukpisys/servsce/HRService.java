package com.sinosoft.stukpisys.servsce;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.entity.*;

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
    List<Map<String,Integer>> getPopulationBySexDiffer();
    List<Map<String,Integer>> getPopulationByEducationDiffer();
    List<String> getDifferEducateName();
    List<Map<String,Integer>> getPopulationByMajorDiffer();
    List<String> getDifferMajorName();
    List<Map<String,Integer>>  getPopulationByIs211();
    List<Map<String,Integer>> getPopulationByLocationDiffer();
    List<String> getDifferLocationName();
//    List<List<Object>> getUserInfoByParam(String HRName,String job,String school,String education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211);
    //3-29 服务traineeController
    Education getEduInfoByUserName(String userName);
    List<List<Object>> getScoreFromStageByUser_id(int userId,int stage);
    List<JSONObject>  getJudgeByParam(String HRName,String job,String school,String Education,String major,String sex,boolean isSimple,boolean isFired,boolean isNew,boolean hasErr,String is211,String enterTime);
    List<JSONObject> getUserScoreParam(String HRName,String job,String school,String Education,String major,String sex,boolean isSimple, boolean isFired,boolean isNew,boolean hasErr,String is211,String enterTime);
    User getByName(String name);

    List<ScoreLabel> getJudgeLabelName();


    List<String> gethrName();
    List<String> getJob();
    List<String> getschoolName();
    List<String> getHighestEducate();
    List<String> getMajor();
    List<String> getEnterTime();




    /**
     * 通过id查询label的index和数值
     * @return
     */
    List<Integer> getScoreList(Integer id);

    /**
     * 按阶段和时间查看总分数（排序）
     * @param stage
     * @param enter_time
     * @return
     */
    List<List<Object>> getUserScoreByStageAndTime(int stage, java.sql.Date enter_time);

    /**
     * 按2或3阶段查看在后5的人员的id
     * @param stage
     * @return
     */
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
    List<Long> getScoreInbackByStage(int stage);
    List<Object> getScoreById(long id);

    /**
     * 按阶段数查看成绩都在后5的人员的成绩
     * @param stages
     * @return
     */
    List<ScoreLabel> getScoreInback(int stages);
    /**
     * 通过id查三个阶段评价
     * @param id
     * @return
     */
    List<ScoreLabel> getJudgeInback(long id);
    /**
     * 通过id查合格不合格和请假天数
     * @param id
     * @return
     */
    List<ScoreLabel> getTraineePass(Long id);

    List<ScoreLabel> getTraineeScore(String name);

    List<ScoreLabel> getTraineeJudge(String name);

    List<ScoreValue> getFirstSealScore();
    List<ScoreValue> getGoodSealScore();
    List<ScoreValue> getUsualPerformance();
    int insertScoreLabel(ScoreLabel scoreLabel);
    List<ScoreLabel> selectScoreLabel();
    int updateLabelName(String newLabelName,String labelName);
    List<ScoreLabel> getLabelName();


    List<JSONObject> getScore();
    List<String> getName();

}
