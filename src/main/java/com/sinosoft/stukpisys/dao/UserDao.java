package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao{
    @Select("select * from user where name=#{name}")
    User getByName(String name);

    @Insert("INSERT INTO user(name,password,role) VALUES(#{name},#{password},#{role})")
    void insert(User user);
    //将读取到的信息插入到education表中  --米晓锐
    @Insert("INSERT INTO education(highest_educate,major,school_name,location,is211,graduation_time) VALUES(#{highestEducate},#{major},#{schoolName},#{location},#{is211},#{graduationTime})")
    void insertEducation(Education education);
    //将读取到的信息插入到UserInfo表中——米晓锐
    @Insert("INSERT INTO user_info(user_name,state,hr_name,job,gender,email,birth,native_place,phone,enter_time,edu_id,dept) VALUES(#{userName},#{state},#{hrName},#{job},#{gender},#{email},#{birth},#{nativePlace},#{phone},#{enterTime},#{eduId},#{dept})")
    void insertUserInfo(UserInfo userInfo);
    //--米晓锐
    @Select("SELECT user_id FROM user WHERE name=#{name}")
    long selectIdByName(String name);

}
