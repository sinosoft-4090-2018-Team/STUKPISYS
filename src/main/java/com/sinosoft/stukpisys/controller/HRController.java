package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hr", produces = "application/json;charset=UTF-8")
public class HRController {

    @Autowired
    private HRService hrService;

//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getAllInfo")
//    public String getAllInfo()
//    {
//        List<UserInfo> userInfoList=hrService.getAllInfo();
//
//        return JSON.toJSONString(userInfoList);
//    }
//
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getEduInfo")
//    public String getEduInfo()
//    {
//        List<Education> userEduInfoList=hrService.getEduInfo();
//        for(int i=0;i<userEduInfoList.size();i++){
//            System.out.println(userEduInfoList.get(i).toString());
//        }
//        return "";
//    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getPersonInfoByName")
    public String getPersonInfoByName(String name)
    {
        UserInfo userInfo=hrService.getPersonInfoByName(name);
        return "";
    }

//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getInfoByState")
//    public String getInfoByState(String state)
//    {
//        List<UserInfo> userInfoList=hrService.getInfoByState(state);
//        return "";
//    }
//
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/distPerson")
//    public String distPerson(String name, String dept)
//    {
//        int count=hrService.distPerson(name,dept);
//        if (1==count){
//            return "";
//        }else{
//            return "";
//        }
//
//    }
}
