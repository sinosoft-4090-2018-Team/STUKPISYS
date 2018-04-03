package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.servsce.HRService;
import freemarker.ext.beans.HashAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Iterator;
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
        List<Map<String,Integer>> map=hrService.getPopulationBySexDiffer();
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

}
