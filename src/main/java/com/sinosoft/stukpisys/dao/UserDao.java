package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    User getByName(String name);

    void getEducation();//查询学历

    void get211scale();//查询2111比例

    void getSexscale();//查询男女占比

    void getSpecialty();//查询专业分布

    void getUniversity();//查询学历

    List<Map<String, Object>> getScoreByHr(String hr);//按面试官查成绩、评价

    List<Map<String, Object>> getScoreByJob(String job);//按岗位查

    List<Map<String, Object>> getScoreBySpecial(String special);//按岗位查

    List<Map<String, Object>> getScoreByEdu(String edu);//按学历查

    List<Map<String, Object>> getScoreBySchool(String school);//按学校查

    void insert(User user);
    //查询所有
    @Select("select * from user_info")
    List<UserInfo> getAllInfo();
    //根据状态查询非正常状态实习生
    @Select("select * from user_info where state=#{0}")
    List<UserInfo> getInfoByState(String state);

    @Select("select * from educate")
    List<Education> getEduInfo();

}
