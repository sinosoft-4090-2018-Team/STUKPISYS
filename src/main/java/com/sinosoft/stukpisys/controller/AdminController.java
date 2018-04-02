package com.sinosoft.stukpisys.controller;

import com.sinosoft.stukpisys.servsce.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/test")
    public String test(){
        return "hello,admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/changeScore")
    public String changeScore(String userName,String labelName,int value){
        //todo
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/changeJudge")
    public String changeJudge(@RequestParam String userName,@RequestParam  String labelName,@RequestParam String judge){
        int count=adminService.changeJudgeByUserNameAndLabelName(userName,labelName,judge);
        if(count!=0){
            return "error";
        }
       return  "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/changeInfo")
    public String changeInfo(String userName,String key,String value){
        //todo
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/setDept")
    public String setDept(String userName,String dept){
        int count=adminService.setDept(userName,dept);
        if(count!=0){
            return "error";
        }
        return  "success";
    }








}
