package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @program: stukpisys
 * @description: 筛选控制
 * @author: ZRTZRT
 * @create: 2018-03-28 21:16
 **/
@CrossOrigin
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
     * @param Education 学历
     * @param major 专业
     * @param sex   性别
     * @param isFired   淘汰
     * @param enterTime   入司时间
     * @param isNew 特殊
     * @param hasErr    严重不符合项
     * @param is211 211
     * @return 成绩和评价
     * 评价字段：
     * 主动性、主动性、灵活度、责任心与沉稳度、展示评价、第一阶段观察与评价、第二阶段观察与评价、第三阶段观察与评价、
     * 责任心、主动性、抗压性、团队意识、学习能力、沟通、严重不符合项、中途退出-淘汰实习生、淘汰阶段、淘汰原因、中途进入-特殊实习生
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/judge" )
    public  List<Map> siftUserJudge(String HRName, String job, String school, String Education, String major, String sex, boolean isSimple, boolean isFired, boolean isNew, boolean hasErr, String is211, String enterTime)  {
      //  PageHelper.startPage(pageNum,5*11);
        List<JSONObject> list=hrService.getJudgeByParam(HRName,job,school,Education,major, sex,isSimple,isFired,isNew, hasErr, is211,enterTime);
        List<Map> jsonObjectList=new LinkedList<>();

        List<String> stringList=hrService.getName();

        for (int i = 0; i < stringList.size(); i++) {

            Map<String, String> stringMap = new LinkedHashMap<>();
            stringMap.put("姓名", stringList.get(i));
            for ( int j=0;j<list.size();j++) {
                if (stringList.get(i).equals(list.get(j).getString("user_name"))) {
                    stringMap.put(list.get(j).getString("label_name"), list.get(j).getString("value_string"));
                }
            }

            jsonObjectList.add(stringMap);
        }


      //  PageInfo<Map> pageInfo = new PageInfo<>(jsonObjectList);
        //jsonObjectList.add(pageInfo);
      //  System.out.print(jsonObjectList);
        return jsonObjectList;


    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/score")
    public String siftUserScore(String HRName,String job,String school,String Education,String major,String sex,boolean isSimple,boolean isFired,boolean isNew,boolean hasErr,String is211,String enterTime) {

        List<JSONObject> list = hrService.getUserScoreParam(HRName, job, school, Education, major, sex,isSimple, isFired, isNew, hasErr, is211,enterTime);
        List<JSONObject> jsonObjectList=new LinkedList<>();
        LinkedHashSet<String> hs =new LinkedHashSet<>();

        for ( int i=0;i<list.size()-2;i++) {
            JSONObject res = new JSONObject();
            if (hs.add(list.get(i).getString("user_name"))){
                res.put("name",list.get(i).getString("user_name"));
                res.put("firstStageScore", list.get(i).getString("value_int"));
                res.put("secondStageScore", list.get(i+1).getString("value_int"));
                res.put("thirdStageScore", list.get(i+2).getString("value_int"));
            }
              /*  res.put("secondStageScore", list.get(i ).getString("value_int"));
                //res.put("thirdStageScore", list.get(i+1).getString("value_int"));
            */
            if(!res.isEmpty()){
                jsonObjectList.add(res);
            }
        }
        return jsonObjectList.toString();
        // return "success";
    }

    /***
     * 筛选两个或三个阶段排名倒五的
     * @param stage 阶段数
     * @return  评价
     */
    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/judgeInBack")
    public String getJudgeInBack(int stage)
    {

        List<List<ScoreLabel>> lll=new ArrayList<>();
        List<Long> l2=new ArrayList<>();

        if(stage==2) {
            l2=hrService.getScoreInbackByStage2(2);
            if (l2.size() != 0) {
                for (int i = 0; i < l2.size(); i++) {

                    lll.add(hrService.getJudgeInback(l2.get(i)));

                }
            }
        }else if(stage==3){
            l2=hrService.getScoreInbackByStage(3);
            if (l2.size() != 0) {
                for (int i = 0; i < l2.size(); i++) {

                    lll.add(hrService.getJudgeInback(l2.get(i)));

                }
            }

        }
        //todo
        return JSON.toJSONString(lll);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/scoreInBack")
    public String getScoreInBack(int stages)
    {
        List<List<Object>> ll=new ArrayList<>();
        List<Long> l1=new ArrayList<>();
        if(stages==3){
        l1=hrService.getScoreInbackByStage(stages);
        if(l1.size()!=0) {
            for (int i = 0; i < l1.size(); i++) {

                ll.add( hrService.getScoreById(l1.get(i)));
            }
        }else{
            System.out.println("无");
        }

        }else if(stages==2) {
            l1=hrService.getScoreInbackByStage2(stages);
            if(l1.size()!=0) {
                for (int i = 0; i < l1.size(); i++) {

                    ll.add( hrService.getScoreById(l1.get(i)));
                }
            }else{
                System.out.println("无");
            }
        }
        return JSON.toJSONString(ll);
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
        List<String> listHr=hrService.gethrName();
        List<String>  listJob=hrService.getJob();
        List<String> listSchoolName=hrService.getschoolName();
        List<String> listHighestEducate=hrService.getHighestEducate();
        List<String> listMajor=hrService.getMajor();
        List<String> listEnterTime=hrService.getEnterTime();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hr",listHr);
        jsonObject.put("job",listJob);
        jsonObject.put("school_name",listSchoolName);
        jsonObject.put("highest_educate",listHighestEducate);
        jsonObject.put("major",listMajor);
        jsonObject.put("enterTime",listEnterTime);

        return jsonObject.toJSONString();
    }
    //
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getScore")
    public String getScore()
    {
        List<JSONObject> list=hrService.getScore();

        List<JSONObject> jsonObjectList=new LinkedList<>();

      //  List<String> stringList=hrService.getName();

        /*for (int i=0;i<list.size();i++){
            for (int j=0;j<3;j++){
                System.out.print(list.get(i).getString("name"));
            }
            System.out.println();
        }*/

   /*     for (int j=0;j<stringList.size();j++){
            for (int i=0 ;i<list.size();i++){
              if(stringList.get(j).contains(list.get(i).getString("name"))) {
                  JSONObject jsonObject = new JSONObject();
                  jsonObject.put("name", list.get(i).getString("name"));
                  if ("1".equals(list.get(i).getString("stage"))) {
                      jsonObject.put("firstStageScore", list.get(i).getString("value_int"));
                  }
                  if ("2".equals(list.get(i).getString("stage"))) {
                      jsonObject.put("secondStageScore", list.get(i ).getString("value_int"));
                  }
                  if ("3".equals(list.get(i).getString("stage"))) {
                      jsonObject.put("thirdStageScore", list.get(i ).getString("value_int"));
                  }

                    jsonObjectList.add(jsonObject);

              }

            }
        }*/
        LinkedHashSet<String> hs =new LinkedHashSet<>();

        for ( int i=0;i<list.size()-2;i++) {
            JSONObject res = new JSONObject();
            if (hs.add(list.get(i).getString("name"))){
                res.put("name",list.get(i).getString("name"));
                res.put("firstStageScore", list.get(i).getString("value_int"));
                res.put("secondStageScore", list.get(i+1).getString("value_int"));
                res.put("thirdStageScore", list.get(i+2).getString("value_int"));
            }
              /*  res.put("secondStageScore", list.get(i ).getString("value_int"));
                //res.put("thirdStageScore", list.get(i+1).getString("value_int"));
            */
              if(!res.isEmpty()){
                  jsonObjectList.add(res);
              }
        }
        return jsonObjectList.toString();
    }






}
