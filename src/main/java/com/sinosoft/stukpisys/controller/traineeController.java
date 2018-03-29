package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.User;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private HRService hrService;

    /***
     * 查基本信息
     * @param name
     * @return 请查info和education，不要直接调用dao返回
     */
    @GetMapping(value ="/info")
    public String getTraineeInfo(String name)
    {
        UserInfo userInfo=hrService.getPersonInfoByName(name);
        Education education=hrService.getEduInfoByUserName(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo",userInfo);
        jsonObject.put("education",education);
        return JSON.toJSONString(jsonObject);
    }

    @GetMapping(value ="/leave")//请假先不写
    public String getTraineeLeave(String name)
    {

        //todo
        return null;
    }

    @GetMapping(value ="/pass")//合格与不合格的
    public String getTraineePass(String name)
    {
        //todo
        return null;
    }

    @GetMapping(value ="/score")//总成绩 sum
    public String getTraineeScore(String name)
    {
        User user=hrService.getByName(name);
        Long userId=user.getUserId();
     //   int score1=hrService.getScoreFromStageByUser_id(userId,1);
//还未完成----------------------今晚继续

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
