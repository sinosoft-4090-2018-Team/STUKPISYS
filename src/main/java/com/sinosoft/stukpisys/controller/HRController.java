package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/hr", produces = "application/json;charset=UTF-8")
public class HRController {

    @Autowired
    private HRService hrService;



//    //hr通过员工名字获取员工信息
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getPersonInfoByName")
//    public String getPersonInfoByName(String name)
//    {
//        UserInfo userInfo=hrService.getPersonInfoByName(name);
//        return JSON.toJSONString(userInfo);
//    }
//
//    //hr 获取自己面试的所有员工信息
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getInfoByState")
//    public String getUserIdByhrName(String hrName)
//    {
//        List<UserInfo> userInfoList=hrService.getUserIdByhrName(hrName);
//        return JSON.toJSONString(userInfoList);
//    }
//    //通过工作意愿筛选员工
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getUserByJob")
//    public String getUserByJob(String job)
//    {
//        List<UserInfo> userInfoList=hrService.getUserByJob(job);
//        return JSON.toJSONString(userInfoList);
//    }
//    //通过性别来挑选员工--------权限有变
//    @PreAuthorize("hasAnyRole( 'MG','ADMIN')")
//    @GetMapping(value ="/getUserBySex")
//    public String getUserBySex(String sex)
//    {
//        List<UserInfo> userInfoList=hrService.getUserBySex(sex);
//        return JSON.toJSONString(userInfoList);
//    }
//    //通过入职时间来筛选-------权限有变
//    @PreAuthorize("hasAnyRole( 'MG','ADMIN')")
//    @GetMapping(value ="/getUserByEnterTime")
//    public String getUserByEnterTime(String entertime)
//    {
//        List<UserInfo> userInfoList=hrService.getUserByEnterTime(entertime);
//        return JSON.toJSONString(userInfoList);
//    }
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getUserBySchool")
//    public String getUserBySchool(String  schoolName)
//    {
//        List<UserInfo> userInfoList=hrService.getUserBySchool(schoolName) ;
//        return JSON.toJSONString(userInfoList);
//    }
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getUserByHighestEducate")
//    public String getUserByHighestEducate(String educate)
//    {
//        List<UserInfo> userInfoList=hrService.getUserByHighestEducate(educate);
//        return JSON.toJSONString(userInfoList);
//    }
//
//    //hr通过状态获取所有员工信息-------权限有变
//    @PreAuthorize("hasAnyRole( 'MG','ADMIN')")
//    @GetMapping(value ="/getInfoByState")
//    public String getInfoByState(String state)
//    {
//        List<UserInfo> userInfoList=hrService.getUserInfoByState(state);
//        return JSON.toJSONString(userInfoList);
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
//
//    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
//    @GetMapping(value ="/getUserScoreByStageAndTime")
//    public String getUserScoreByStageAndTime(int stage, Date enterTime)
//    {
//        List<List<Object>> userInfoList=hrService.getUserScoreByStageAndTime(stage,enterTime);
//
//        return JSON.toJSONString(userInfoList);
//    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value ="/insertLabel")
    public String insertLabel(ScoreLabel scoreLabel)
    {
        int count=hrService.insertScoreLabel(scoreLabel);
        if (count==1){
            return "成功";
        }else
        return "失败";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value ="/selectScoreLabel")
    public String selectScoreLabel() {
        List<ScoreLabel> scoreLabelsList = new ArrayList<ScoreLabel>();
        scoreLabelsList = hrService.selectScoreLabel();
        return JSON.toJSONString(scoreLabelsList);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value ="/updateLabelName")
    public String updateLabelName(String newLabelName, String labelName) {
        int count=hrService.updateLabelName(newLabelName,labelName);
        if (count==1){
            return "修改成功";
        }else
            return "修改失败";
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR','MG')")
    @GetMapping(value ="/getLabelName")
    public String getLabelName() {
        List<ScoreLabel> labelList=hrService.getLabelName();
        return JSON.toJSONString(labelList);
    }


}
