package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: stukpisys
 * @description: 筛选控制
 * @author: ZRTZRT
 * @create: 2018-03-28 21:16
 **/
@RestController
@RequestMapping(value = "/sift", produces = "application/json;charset=UTF-8")
public class siftController {
    @Autowired
    HRService hrService;

    /***
     * 筛选用户
     * @param HRName 面试官
     * @param job   应聘岗位
     * @param school    学校
     * @param education 学历
     * @param major 专业
     * @param sex   性别
     * @param isFired   淘汰
     * @param isNew 特殊
     * @param hasErr    严重不符合项
     * @param is211 211
     * @return 成绩和评价
     * 评价字段：
     * 主动性、主动性、灵活度、责任心与沉稳度、展示评价、第一阶段观察与评价、第二阶段观察与评价、第三阶段观察与评价、
     * 责任心、主动性、抗压性、团队意识、学习能力、沟通、严重不符合项、中途退出-淘汰实习生、淘汰阶段、淘汰原因、中途进入-特殊实习生
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/judge")
    public String siftUserJudge(@RequestParam(value="HRName",required=false) String HRName,@RequestParam(value="job",required=false)  String job,@RequestParam(value="school",required=false)  String school,@RequestParam(value="education",required=false)  String education,@RequestParam(value="major",required=false)  String major,@RequestParam(value="sex",required=false)  boolean sex,@RequestParam(value="isFired",required=false)  boolean isFired,@RequestParam(value="isNew",required=false)  boolean isNew,@RequestParam(value="hasErr",required=false)  boolean hasErr,@RequestParam(value="is211",required=false)  boolean is211 )
    {
        //todo

       List<ScoreValue> list=hrService.getJudgeByParam(HRName,job,school,Education,major, sex,isFired,isNew, hasErr, is211);


     //   System.out.println(list.get(0).getUserId());
       return JSON.toJSONString(list);
       // return "success";
      // List<List<Object>> list=hrService.getUserInfoByParam(HRName,job,school,education,major,sex,isFired, isNew,hasErr,is211);
        return "";
    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/score")
    public String siftUserScore(String HRName,String job,String school,String Education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211)
    {
        //todo
        return null;
    }

    /***
     * 筛选两个或三个阶段排名倒五的
     * @param stage 阶段数
     * @return  成绩和评价
     */
    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/judgeInBack")
    public String getJudgeInBack(int stage)
    {
        //todo
        return null;
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/scoreInBack")
    public String getScoreInBack(int stage)
    {
        //todo
        return null;
    }

    /***
     * 获取筛选条件
     * @return
     * [{'HR':[]},{'job':[]},{'school':[]},{'Education':[]},{'major':[]}]
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getSiftTerms")
    public String getSiftTerms()
    {
        //todo
        return null;
    }
}
