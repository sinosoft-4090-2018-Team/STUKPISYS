package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value ="/pass")//合格与不合格与请假天数
    public String getTraineePass(String name)
    {
        List<ScoreLabel> listList= hrService.getTraineePass(hrService.getByName(name).getUserId());
        return JSON.toJSONString(listList);
    }

    @GetMapping(value ="/score")//小分，阶段总成绩 sum
    public String getTraineeScore(String name)
    {
         List<ScoreLabel> listList= hrService.getTraineeScore(name);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("score",listList);

     //   listList.set("")


        return  jsonObject.toJSONString();
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
        List<ScoreLabel> listList= hrService.getTraineeJudge(name);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("judge",listList);
        return jsonObject.toJSONString();
    }


}
