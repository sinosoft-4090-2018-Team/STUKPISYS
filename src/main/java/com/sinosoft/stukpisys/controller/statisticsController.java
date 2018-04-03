package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
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
        Map<String,Integer>map=hrService.getPopulationBySexDiffer();
        return JSON.toJSONString(map);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/education")//OK
    public String educationStatistics()
    {
        List<Map<String,Integer>>map=hrService.getPopulationByEducationDiffer();

        return JSON.toJSONString(map);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/major")//OK
    public String majorStatistics()
    {
        List<Map<String,Integer>>map=hrService.getPopulationByMajorDiffer();
        return JSON.toJSONString(map);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/211")//ok
    public String schoolStatistics()
    {
        Map<String,Integer>map=hrService.getPopulationByIs211();
        return JSON.toJSONString(map);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/locat")//OK
    public String locationStatistics()
    {
        List<Map<String,Integer>>map=hrService.getPopulationByLocationDiffer();
        return JSON.toJSONString(map);
    }


    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/calculate")//OK
    public String calculateScore()
    {
       List<ScoreValue> listFirstSeal=hrService.getFirstSealScore();
       List<ScoreValue> listGoodSeal=hrService.getGoodSealScore();
       List<ScoreValue> listUsualPerformance=hrService.getUsualPerformance();
       long sum=0;
       JSONObject jsonObject=new JSONObject();
       List<JSONObject> jsonObjectList=new LinkedList<>();
        for (int i=0;i<listFirstSeal.size();i++){
            for (int j=0;j<listGoodSeal.size();j++){
                for (int k=0;k<listUsualPerformance.size();k++){
                    if(listFirstSeal.get(i).getUserId()==listGoodSeal.get(j).getUserId()&&listFirstSeal.get(i).getUserId()==listUsualPerformance.get(k).getUserId()&&listUsualPerformance.get(k).getUserId()==listGoodSeal.get(j).getUserId()){
                        sum=listFirstSeal.get(i).getScore()+listFirstSeal.get(j).getScore()+listUsualPerformance.get(k).getScore();
                        jsonObject.put("userId",listFirstSeal.get(i).getUserId());
                        jsonObject.put("sum",sum);
                        jsonObjectList.add(jsonObject);
                    }

            }

           }

        }

        return jsonObjectList.toString();
    }

}
