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

}
