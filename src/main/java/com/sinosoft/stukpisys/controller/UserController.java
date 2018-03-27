package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) throws AuthenticationException {
        return userService.login(username, password);
//        return "login";
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        System.out.println(user.toString());
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

    @GetMapping(value ="/getAllInfo")
    public String getAllInfo()
    {
        List<UserInfo> userInfoList=userService.getAllInfo();

         return JSON.toJSONString(userInfoList);
    }

    @GetMapping(value ="/getEduInfo")
    public String getEduInfo()
    {
        List<Education> userEduInfoList=userService.getEduInfo();
        for(int i=0;i<userEduInfoList.size();i++){
            System.out.println(userEduInfoList.get(i).toString());
        }
        return "";
    }

    @GetMapping(value ="/getPersonInfoByName")
    public String getPersonInfoByName(@RequestHeader String name)
    {
       UserInfo userInfo=userService.getPersonInfoByName(name);
        return "";
    }

    @GetMapping(value ="/getInfoByState")
    public String getInfoByState(@RequestHeader String state)
    {
        List<UserInfo> userInfoList=userService.getInfoByState(state);
        return "";
    }

    @GetMapping(value ="/distPerson")
    public String distPerson(@RequestHeader String name, @RequestHeader  String dept)
    {
        int count=userService.distPerson(name,dept);
        if (1==count){
            return "";
        }else{
            return "";
        }

    }

    }
