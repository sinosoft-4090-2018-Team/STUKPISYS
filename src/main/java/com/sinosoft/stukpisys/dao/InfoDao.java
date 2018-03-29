package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    @Select("SELECT COUNT(*) FROM user_info WHERE gender=#{0};")
    int getPopulationBySexDiffer(int gender);
    //更具学历来差异来查询人数
    @Select("SELECT COUNT(*) FROM education WHERE highest_educate=#{0}")
    int getPopulationByEducationDiffer(String education);
    @Select("SELECT DISTINCT highest_educate FROM education ")
    List<String> getDifferEducateName();
    //更具专业来差异来查询人数
    @Select("SELECT COUNT(*) FROM education WHERE  major=#{0}")
    int getPopulationByMajorDiffer(String major);
    @Select("SELECT DISTINCT major FROM education ")
    List<String> getDifferMajorName();
    //获取211学校的数量
    @Select("SELECT COUNT(*) FROM education WHERE  is211=#{0};")
    int getPopulationByIs211(int is211);
    //更具地点来差异来查询人数
    @Select("SELECT COUNT(*) FROM education WHERE  location=#{0}")
    int getPopulationByLocationDiffer(String location);
    @Select("SELECT DISTINCT location FROM education")
    List<String> getDifferLocationName();



   @Select("\t<script> \" +\n" +
           "            \"SELECT education.*,d.* \" +\n" +
           "            \"from education,\n" +
           "\t\t\t\t\t\t(SELECT user_info.*,c.* FROM user_info ,\n" +
           "\t\t\t\t\t\t(SELECT user.name,b.* from user, \n" +
           "\t\t\t\t\t\t(select score_value.user_id,a.* from score_value,\n" +
           "\t\t\t\t\t\t(select * from score_label where belong='err') a\n" +
           "\t\t\t\t\t\t\twhere a.label_index=score_value.label_index) b\n" +
           "\t\t\t\t\t\t\twhere user.user_id=b.user_id) c\n" +
           "\t\t\t\t\t\t\twhere user_info.user_name=c.name) d\" +\n" +
           "            \" <where> \" +\n" +
           "            \" <if test=\\\"hr_name != null\\\">hr_name=#{HRName}</if> \" +\n" +
           "            \" <if test=\\\"job != null\\\"> AND job=#{job}</if> \" +\n" +
           "            \" <if test=\\\"school_name != null\\\"> AND school_name=#{school}</if> \" +\n" +
           "\t\t\t\t\t\t\" <if test=\\\"highest_educate != null\\\"> AND highest_educate=#{education}</if> \" +\n" +
           "\t\t\t\t\t\t\" <if test=\\\"major != null\\\"> AND major=#{major}</if> \" +\n" +
           "\t\t\t\t\t\t\" <if test=\\\"sex != null\\\"> AND sex=#{sex}</if> \" +\n" +
           "\t\t\t\t\t\t\" <if test=\\\"state != null\\\"> AND state=#{state }</if> \" +\n" +
           "\t\t\t\t\t\t\" <if test=\\\"is211 != null\\\"> AND is211=#{is211}</if> \" +\n" +
           "\t\t\t      \" <if test=\\\"belong != null\\\"> AND belong=#{belong}</if> \" +\n" +
           "\t\t\t\t\t\t\"  and education.edu_id=d.edu_id\"\n" +
           "            \" </where> \" +\n" +
           "            \n" +
           "            \" </script> ")
    List<List<Object>> getUserInfoByParam(String HRName, String job, String school, String education, String major, boolean sex, String state, String belong, boolean is211);


}
