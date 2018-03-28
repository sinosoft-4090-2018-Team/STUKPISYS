package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
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
    //通过hr名字查询实习生id
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
    List<UserInfo> getAllInfo();
    //根据状态查询非正常状态实习生
    @Select("select * from user_info where state=#{0}")
    List<UserInfo> getInfoByState(String state);
    //查学对应员工的学历信息
    @Select("SELECT *\n" +
            "FROM education\n" +
            "WHERE edu_id=(SELECT edu_id\n" +
            "FROM user_info\n" +
            "where user_name= #{0})")
    List<Education> getEduInfoByUserName(String userName);

}
