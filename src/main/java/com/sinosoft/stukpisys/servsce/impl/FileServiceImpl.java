package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.*;
import com.sinosoft.stukpisys.servsce.FileService;
import com.sinosoft.stukpisys.untils.GetExcelMessage;
import com.sinosoft.stukpisys.untils.LeadTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private InfoDao infoDao;


    @Override
    @Transactional
    public String readExcel(File excel, String type) {
        List<List<Object>> data = null;
        try {
//            data = ExcelReader.get(excel);
            data = GetExcelMessage.getExcel(excel.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if("user".equals(type)) {
            return saveUser(data);
        }
        if("score".equals(type)) {
            return saveScore(data);
        }
        return null;
    }


    private String saveUser(List<List<Object>> excel){
/*
       for(int i=0;i<excel.size();i++){
            for(int j=0;j<excel.get(i).size();j++){
                System.out.print(Integer.toString(i)+excel.get(i).get(j)+"  ");
            }
            System.out.println();
        }
*/

        //todo1、插入user表；2、插入education表；3、插入userinfo表*//*
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(int i=1;i<excel.size();i++){
            //1
            if (userDao.getByName((String)excel.get(i).get(0)) != null) {
                continue;
            }else{
                User user=new User((String)excel.get(i).get(0),encoder.encode("000000"),"ROLE_USER");
                //调用插入user表的方法
                userDao.insert(user);
            }
           // System.out.println((String)excel.get(i).get(13));
            //2
            //构造函数中学校地点为空

            Education education =new Education((String)excel.get(i).get(9),(String)excel.get(i).get(10),(String)excel.get(i).get(11),"", LeadTo.is211((String)excel.get(i).get(11)),GetExcelMessage.strToDate((String) excel.get(i).get(12)));
            //调用插入education表的方法
            Long eduId=userDao.selectEduIdByEMessage(education);
            if (eduId==null){
                userDao.insertEducation(education);
            }

            //3
            UserInfo userInfo =new UserInfo();
            userInfo.setUserName((String)excel.get(i).get(0));
            userInfo.setState((String)excel.get(i).get(1));
            userInfo.setJob((String)excel.get(i).get(2));
            userInfo.setHrName((String)excel.get(i).get(3));
            userInfo.setDept("");
            if ("男".equals((String)excel.get(i).get(5))){
                userInfo.setGender(0);
            }else if ("女".equals((String)excel.get(i).get(5))){
                userInfo.setGender(1);
            }
            userInfo.setEmail((String)excel.get(i).get(6));
            userInfo.setBirth(GetExcelMessage.strToDate((String)excel.get(i).get(7)));
            userInfo.setNativePlace((String)excel.get(i).get(8));
            userInfo.setPhone(excel.get(i).get(13).toString());
            userInfo.setEnterTime(GetExcelMessage.strToDate((String)excel.get(i).get(14)));
            userInfo.setEduId(userDao.selectEduIdByEMessage(education));
            //调用插入userinfo表的方法
            userDao.insertUserInfo(userInfo);
            //判断user中是否有该面试官，如果没有则要添加,
            User u=userDao.getByName(userInfo.getHrName());
            if (u==null){
                User user1=new User(userInfo.getHrName(),encoder.encode("000000"),"ROLE_HR");
                //添加HR
                userDao.insert(user1);
            }
        }
        return "成功";
    }



    private String saveCourses(List<List<Object>> excel){
        //todo
        return null;
    }

    private String saveScore(List<List<Object>> excel){
        //todo

 /*       for(int i=0;i<excel.size();i++){
            for(int j=0;j<excel.get(i).size();j++){
                System.out.print(Integer.toString(i)+excel.get(i).get(j)+"  ");
            }
            System.out.println();
        }*/
        //插入表头信息
        int a=0,b=0,c=0,d=0;
        String str1="",str2="",str3="",str4="";
        ScoreLabel scoreLabel=new ScoreLabel("",1,0,0,"");
        for(int i=0;i<excel.get(0).size();i++){
            String str=String.valueOf(excel.get(0).get(i));
            if(str.equals("第一阶段\n阶段考试")){
                a=i;
                str1="第一阶段";
            }else if(str.contains("第二阶段")){
                b=i;
                str2="第二阶段";
            }else if(str.contains("第三阶段")) {
                c = i;
                str3="第三阶段";
            }else if(str.equals("4090观察评价")){
                d=i;
                str4="评价阶段";
            }
        }

        //
        //第一阶段信息
        for(int j=a;j<b;j++){

            if (((String)excel.get(0).get(j)).equals("")&&j!=a){
                excel.get(0).set(j,excel.get(0).get(j-1));
            }
            if (((String)excel.get(1).get(j)).equals("")&&j!=a){
                excel.get(1).set(j,excel.get(1).get(j-1));
            }
            String labelName=((String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j)).trim();
            ScoreLabel scoreLabel1= scoreDao.selectLabelByLabelNameAndType(labelName,1);
            if (scoreLabel1==null){
                long labelIndex = scoreDao.selectMaxLabelIndex();
                if (labelIndex!=0){
                    labelIndex=scoreDao.selectMaxLabelIndex();
                }
                labelIndex=labelIndex+1;



                if(((String)excel.get(1).get(j)).equals("第一阶段成绩")){
                    scoreLabel=new ScoreLabel(labelName,labelIndex,0,1,"sum");
                }else if(((String)excel.get(1).get(j)).contains("评价")){
                    scoreLabel=new ScoreLabel(labelName,labelIndex,1,1,"judge");
                }else if(labelName.contains("标准分")){
                    scoreLabel=new ScoreLabel(labelName,labelIndex,0,1,"score");
                }else if(labelName.contains("章")){
                    scoreLabel=new ScoreLabel(labelName,labelIndex,0,1,"seal");
                }else if(labelName.contains("请假，天数")){
                    scoreLabel=new ScoreLabel(labelName,labelIndex,0,1,"absence");
                }else{
                    scoreLabel=new ScoreLabel(labelName,labelIndex,0,1,"");
                }
                //调用插入score_label表中
                scoreDao.insertScore_label(scoreLabel);
            }
        }
        //第二阶段信息
        for(int j=b;j<c;j++){
            if (((String)excel.get(0).get(j)).equals("")&&j!=b){
                excel.get(0).set(j,excel.get(0).get(j-1));
            }
            if (((String)excel.get(1).get(j)).equals("")&&j!=b){
                excel.get(1).set(j,excel.get(1).get(j-1));
            }
            String labelName=((String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j)).trim();
            ScoreLabel scoreLabel1= scoreDao.selectLabelByLabelNameAndType(labelName, 2);
            if (scoreLabel1==null){


                long labelIndex = scoreDao.selectMaxLabelIndex();
                if (labelIndex!=0){
                    labelIndex=scoreDao.selectMaxLabelIndex();
                }
                labelIndex=labelIndex+1;


                if (((String) excel.get(1).get(j)).equals("第二阶段成绩")) {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 0, 2, "sum");
                } else if (((String) excel.get(1).get(j)).contains("评价")) {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 1, 2, "judge");
                } else {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 0, 2, "");
                }
                //调用插入score_label表中
                scoreDao.insertScore_label(scoreLabel);

            }
        }
        //第三阶段信息
        for(int j=c;j<d;j++){
            if (((String)excel.get(0).get(j)).equals("")&&j!=c){
                excel.get(0).set(j,excel.get(0).get(j-1));
            }
            if (((String)excel.get(1).get(j)).equals("")&&j!=c){
                excel.get(1).set(j,excel.get(1).get(j-1));
            }
            String labelName=((String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j)).trim();
            ScoreLabel scoreLabel1= scoreDao.selectLabelByLabelNameAndType(labelName, 3);
            if (scoreLabel1==null){

                long labelIndex = scoreDao.selectMaxLabelIndex();
                if (labelIndex!=0){
                    labelIndex=scoreDao.selectMaxLabelIndex();
                }
                labelIndex=labelIndex+1;
                if (((String) excel.get(1).get(j)).equals("第三阶段成绩")) {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 0, 3, "sum");
                } else if (((String) excel.get(1).get(j)).contains("评价")) {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 1, 3, "judge");
                } else {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 0, 3, "");
                }
                //调用插入score_label表中
                scoreDao.insertScore_label(scoreLabel);
            }

        }
        //评价阶段
        for(int j=d;j<excel.get(0).size();j++){
            if (((String)excel.get(0).get(j)).equals("")&&j!=d){
                excel.get(0).set(j,excel.get(0).get(j-1));
            }
            if (((String)excel.get(1).get(j)).equals("")&&j!=d){
                excel.get(1).set(j,excel.get(1).get(j-1));
            }
            String labelName=((String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j)).trim();
            ScoreLabel scoreLabel1= scoreDao.selectLabelByLabelNameAndType(labelName, 0);
            if (scoreLabel1==null){

                long labelIndex = scoreDao.selectMaxLabelIndex();
                if (labelIndex!=0){
                    labelIndex=scoreDao.selectMaxLabelIndex();
                }
                labelIndex=labelIndex+1;


                if (((String) excel.get(1).get(j)).equals("严重不符合项")) {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 1, 0, "err");
                } else {
                    scoreLabel = new ScoreLabel(labelName, labelIndex, 1, 0, "judge");
                }
                //调用插入score_label表中
                scoreDao.insertScore_label(scoreLabel);
            }
        }

        //插入表内容
        long [] labelIndex =new long [excel.get(1).size()];
        int[] isUpdate=new int[excel.get(1).size()];

        for(int i=3;i<excel.size();i++){
            long userId=0;
            String userName=(String)excel.get(i).get(1);
            //调用根据username得出userId方法
            userId=userDao.selectIdByName(userName);
            //System.out.print(userId);
            if ((scoreDao.selectMessageByUserId(userId)).size()!=0){
                for(int j=a;j<excel.get(i).size();j++){
                    String labelName= (String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j);
                    long labelIndex1=scoreDao.selectLabelIndexByLabelName(labelName);

                    long valueInt=0;
                    String valueString="";
                    String value=(String)excel.get(i).get(j);
                    boolean isIntOrString=false;
                    for (int z=0;z<value.length();z++){
                        if(value.charAt(z)>=48 && value.charAt(z)<=57){
                            isIntOrString=true;
                        }
                    }
                    if(("".equals((String)excel.get(i).get(j))) || isIntOrString){
                        if (value.equals("")){
                            value="0";
                        }
                        valueInt=Long.parseLong(value.split("\\.", 2)[0]);
                        valueString=null;
                    }else{
                        valueInt=0;//接口搜索时应该搜索valueString!=nul
                        valueString=value;
                    }
                    scoreDao.updateScoreValue(userId,labelIndex1,valueInt,valueString);
                }
            }else{
                for(int j=a;j<excel.get(i).size();j++){

                    String labelName= (String)excel.get(0).get(j)+"，"+(String)excel.get(1).get(j)+"，"+(String)excel.get(2).get(j);
                    //System.out.print(labelName);
                    //
                    if(i==3){
                        long labelIndex1=scoreDao.selectLabelIndexByLabelName(labelName);
                        labelIndex[j]=labelIndex1;
                    }

                    long valueInt=0;
                    String valueString="";
                    String value=(String)excel.get(i).get(j);
                    boolean isIntOrString=false;
                    for (int z=0;z<value.length();z++){
                        if(value.charAt(z)>=48 && value.charAt(z)<=57){
                            isIntOrString=true;
                        }
                    }
                    if(("".equals((String)excel.get(i).get(j))) || isIntOrString){
                        if (value.equals("")){
                            value="0";
                        }
                        valueInt=Long.parseLong(value.split("\\.", 2)[0]);
                        valueString=null;
                    }else{
                        //update label表中的type类型为1，即type为String  根据labelIndex
                        //可以优化
                        if (isUpdate[j]==0){
                            long type=scoreDao.selectTypeis1(labelIndex[j]);
                            if (type==0){
                                scoreDao.updateScoreLabel(labelIndex[j]);
                                isUpdate[j]=1;
                            }
                        }
                        valueInt=0;//接口搜索时应该搜索valueString!=nul
                        valueString=value;
                    }
                    ScoreValue scoreValue=new ScoreValue(userId,labelIndex[j],valueInt,valueString,null);
                    //调用插入value表信息
                    scoreDao.insertScoreValue(scoreValue);
                }
            }
        }
        return "成功";
    }
}