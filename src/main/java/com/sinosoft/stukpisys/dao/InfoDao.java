package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface InfoDao {
    //按学校名字查询  ok
    @Select("select * from user_info where school_name=#{0} ")
    List<UserInfo> getUserBySchoolName(String name);
    //按学历来查询   ok
    @Select("select * from user_info where  highest_educate=#{0} ")
    List<UserInfo>getUserByHighestEducate (String educate);
    //按名字获取一个人的详细信息 ok
    @Select("select * from user_info where user_name=#{name}")
    UserInfo getPersonInfoByName(String name);

    //给实习生分配部门
    @Update("update user_info set dept=#{dept} where user_name=#{name}")
    int distPerson(String name,String dept);
    //通过hr名字查询实习生
    @Select("select * from user_info where hr_name=#{hr}")
    List<UserInfo> getUserIdByhrName(String hr);

    //通过实习生id查询label的index和值
    @Select("select label_index,value_int from score_value where user_id=#{id}")
    List<Integer> getScoreList(Integer id);

    //通过应聘岗位查询实习生信息  ok
    @Select("select * from user_info where job=#{job}")
    List<UserInfo> getUserByJob(String job);

    //通过性别查询实习生信息   ok
    @Select("select * from user_info where sex=#{sex}")
    List<UserInfo> getUserBySex(String sex);

    //通过入司时间查询实习生信息   ok
    @Select("select * from user_info where enterTime=#{enterTime}")
    List<UserInfo> getUserByEnterTime(String  enterTime);

    //查询所有
    @Select("select * from user_info")
    List<UserInfo> getAllUserInfo();
    //根据状态查询非正常状态实习生
    @Select("select * from user_info where state=#{0}")
    List<UserInfo> getInfoByState(String state);
    //查学对应员工的学历信息
    @Select("SELECT *\n" +
            "FROM education\n" +
            "WHERE edu_id=(SELECT edu_id\n" +
            "FROM user_info\n" +
            "where user_name= #{0})")
    Education getEduInfoByUserName(String userName);
    //根据性别差异来查询人数
    @Select("SELECT gender name,COUNT(gender) value FROM user_info GROUP BY gender")
    List<Map<String,Integer>> getPopulationBySexDiffer();
    //更具学历来差异来查询人数
    @Select("SELECT highest_educate name ,COUNT(highest_educate) value  FROM education GROUP BY highest_educate ")
    List<Map<String,Integer>> getPopulationByEducationDiffer();
    //输出所有学历名称
    @Select("SELECT DISTINCT highest_educate FROM education ")
    List<String> getDifferEducateName();
    //更具专业来差异来查询人数
    @Select("SELECT major name ,COUNT(major) value  FROM education GROUP BY major  ")
    List<Map<String,Integer>> getPopulationByMajorDiffer();
    //获取所有不同专业的名称
    @Select("SELECT DISTINCT major FROM education")
    List<String> getDifferMajorName();
    //获取211学校的数量
    @Select("SELECT is211 name ,COUNT(is211) value FROM education GROUP BY is211 ;")
    Map<String,Integer>  getPopulationByIs211();
    //更具地点来差异来查询人数
    @Select("SELECT location name ,COUNT(location) value  FROM education GROUP BY location ")
    List<Map<String,Integer>> getPopulationByLocationDiffer();
    //获取不同的学校的地点
    @Select("SELECT DISTINCT location FROM education")
    List<String> getDifferLocationName();
   //获取评价
   @SelectProvider(type = SqlProvider.class, method = "getUserParam")
    List<ScoreValue> getJudgeByParam(String HRName ,String job,String school,String Education,String major,boolean sex, String state,String belong,boolean is211);
   //获取分数
    @SelectProvider(type = SqlProvider.class, method = "getUserScoreParam")
    List<ScoreValue> getUserScoreParam(String HRName ,String job,String school,String Education,String major,boolean sex, String state,String belong,boolean is211);
    //分配部门
    @Update("update user_info set dept=#{dept} where user_name=#{userName}")
    int setDept(@Param("userName")String userName,@Param("dept") String dept);


    //获取筛选条件(hr_name)
    @Select("select hr_name from user_info GROUP BY hr_name")
    List<UserInfo> gethrName();
    //获取筛选条件(job)
    @Select("select job from user_info GROUP BY job")
    List<UserInfo> getJob();
    //获取筛选条件(school_name)
    @Select("select school_name from education GROUP BY school_name")
    List<Education> getschoolName();
    //获取筛选条件(highest_education)
    @Select("select highest_educate from education GROUP BY highest_educate")
    List<Education> getHighestEducate();
    //获取筛选条件(major)
     @Select("select major from education GROUP BY major")
     List<Education> getMajor();

     @Select("SELECT score_label.*,b.value_int from score_label,\n" +
             "(select score_value.label_index,score_value.value_int from  score_value,(select user.user_id from user where name=#{name}) a\n" +
             "where score_value.user_id=a.user_id)b\n" +
             "WHERE score_label.label_index=b.label_index and belong in('','sum')\n" +
             "and score_label.type='0'")
      List<ScoreLabel> getTraineeScore(String name);


     @Select("SELECT score_label.*,b.value_string from score_label,\n" +
             "(select score_value.label_index,score_value.value_string from  score_value,(select user.user_id from user where name=#{name}) a\n" +
             "where score_value.user_id=a.user_id)b\n" +
             "WHERE score_label.label_index=b.label_index and belong='judge'\n" +
             "and score_label.type='1'")
     List<ScoreLabel> getTraineeJudge(String name);
}
