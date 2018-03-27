package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao{

    @Insert("insert into user(admin,createTime) value(#{admin},#{createTime})")
    int insert(User user);

}
