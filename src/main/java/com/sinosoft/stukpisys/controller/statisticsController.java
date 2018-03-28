package com.sinosoft.stukpisys.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stukpisys
 * @description: 统计控制类
 * @author: ZRTZRT
 * @create: 2018-03-28 21:21
 **/
@RestController
@RequestMapping(value = "/statistics", produces = "application/json;charset=UTF-8")
public class statisticsController {

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/sex")
    public String sexStatistics()
    {
        //todo
        return null;
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/education")
    public String educationStatistics()
    {
        //todo
        return null;
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/major")
    public String majorStatistics()
    {
        //todo
        return null;
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/211")
    public String schoolStatistics()
    {
        //todo
        return null;
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/locat")
    public String locationStatistics()
    {
        //todo
        return null;
    }
}
