package com.sinosoft.stukpisys.controller;

import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getUserByJob")
    public List<UserInfo> getUserByJob(String job)
    {
        List<UserInfo> userInfoList=adminService.getUserByJob(job);
        return userInfoList;
    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getUserBySex")
    public List<UserInfo> getUserBySex(String sex)
    {
        List<UserInfo> userInfoList=adminService.getUserBySex(sex);
        return userInfoList;
    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getUserByEnterTime")
    public List<UserInfo> getUserByEnterTime(String entertime)
    {
        List<UserInfo> userInfoList=adminService.getUserByEnterTime(entertime);
        return userInfoList;
    }

}
