package com.sinosoft.stukpisys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stukpisys
 * @description: 实习生数据控制类
 * @author: ZRTZRT
 * @create: 2018-03-28 21:27
 **/
@RestController
@RequestMapping(value = "/trainee", produces = "application/json;charset=UTF-8")
public class traineeController {

    /***
     * 查基本信息
     * @param name
     * @return 请查info和education，不要直接调用dao返回
     */
    @GetMapping(value ="/info")
    public String getTraineeInfo(String name)
    {
        //todo
        return null;
    }

    @GetMapping(value ="/leave")
    public String getTraineeLeave(String name)
    {
        //todo
        return null;
    }

    @GetMapping(value ="/pass")
    public String getTraineePass(String name)
    {
        //todo
        return null;
    }

    @GetMapping(value ="/score")
    public String getTraineeScore(String name)
    {
        //todo
        return null;
    }

    /***
     * 查态度
     * @param name
     * @return
     * 主动性-第一名章、主动性-good章、灵活度、责任心与沉稳度、展示评价
     */
    @GetMapping(value ="/attitude")
    public String getTraineeAttitude(String name)
    {
        //todo
        return null;
    }

    /***
     * 查评价
     * @param name
     * @return
     * 第一阶段观察与评价、、第三阶段观察与评价、责任心、主动性、 抗压性、团队意识、学习能力、沟通、 严重不符合项
     */
    @GetMapping(value ="/judge")
    public String getTraineeJudge(String name)
    {
        //todo
        return null;
    }


}
