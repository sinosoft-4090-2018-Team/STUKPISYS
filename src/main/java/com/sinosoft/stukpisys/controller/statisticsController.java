package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: stukpisys
 * @description: 统计控制类
 * @author: ZRTZRT
 * @create: 2018-03-28 21:21
 **/
@RestController
@RequestMapping(value = "/statistics", produces = "application/json;charset=UTF-8")
public class statisticsController {
    @Autowired
    private HRService hrService;

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/sex")
    public String sexStatistics()
    {
        int menNumber=hrService.getPopulationBySexDiffer(0);
        int madamNumber=hrService.getPopulationBySexDiffer(1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("男",menNumber);
        jsonObject.put("女",madamNumber);
        return JSON.toJSONString(jsonObject);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/education")
    public String educationStatistics()
    {
        List<String> educationlist=hrService.getDifferEducateName();
        JSONObject jsonObject=new JSONObject();
        for (String education:educationlist){
            int educationNumber=hrService.getPopulationByEducationDiffer(education);
            jsonObject.put(education,educationNumber);
        }
        return JSON.toJSONString(jsonObject);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/major")
    public String majorStatistics()
    {
        List<String> majorlist=hrService.getDifferMajorName();
        JSONObject jsonObject = new JSONObject();
        for (String major:majorlist){
            int majorNumber=hrService.getPopulationByMajorDiffer(major);
            jsonObject.put(major,majorNumber);
        }
        return JSON.toJSONString(jsonObject);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/211")
    public String schoolStatistics()
    {
        int is211Number=hrService.getPopulationByIs211(1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("is211", is211Number);
        return JSON.toJSONString(jsonObject);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/locat")
    public String locationStatistics()
    {
        List<String> locationList=hrService.getDifferLocationName();
        JSONObject jsonObject = new JSONObject();
        for (String locaton: locationList){
            int locationNumber=hrService.getPopulationByLocationDiffer(locaton);
            jsonObject.put(locaton,locationNumber);
        }
        return JSON.toJSONString(jsonObject);
    }
}
