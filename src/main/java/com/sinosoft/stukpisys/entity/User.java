package com.sinosoft.stukpisys.entity;

import java.util.List;

public class User extends BaseEntity{

    private Integer userId;
    private String name;
    private String password;
    private List<String> roles;
    //private Score score;//成绩嵌入类

    public User(Integer userId, String name, String password, List<String> roles) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
