package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao{

    User getByName(String name);

    public void getEducation();//查询学历
    public void get211scale();//查询2111比例
    public  void getSexscale();//查询男女占比
    public void getSpecialty();//查询专业分布
    public void getUniversity();//查询学历


    public List<Map<String,Object>> getScoreByHr(String hr);//按面试官查成绩、评价
    public List<Map<String,Object>> getScoreByJob(String job);//按岗位查
    public List<Map<String,Object>> getScoreBySpecial(String special);//按岗位查
    public List<Map<String,Object>> getScoreByEdu(String edu);//按学历查
    public List<Map<String,Object>> getScoreBySchool(String school);//按学校查


    void insert(User user);
    @Insert("insert into user(admin,createTime) value(#{admin},#{createTime})")
    int insert(User user);

}
