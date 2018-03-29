package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.*;
import com.sinosoft.stukpisys.servsce.FileService;
import com.sinosoft.stukpisys.untils.ExcelReader;
import com.sinosoft.stukpisys.untils.GetExcelMessage;
import com.sinosoft.stukpisys.untils.Is211;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private UserDao userDao;

    @Override
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
        if("courses".equals(type)) {
            return saveCourses(data);
        }
        if("score".equals(type)) {
            return saveScore(data);
        }
        return null;
    }

    private String saveUser(List<List<Object>> excel){
        //todo
        /*1、插入user表；2、插入education表；3、插入userinfo表*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(int i=1;i<excel.size();i++){
            //1
            User user=new User((String)excel.get(i).get(0),encoder.encode("000000"),"ROLE_USER");
            //调用插入user表的方法
            userDao.insert(user);
            //2
            //构造函数中学校地点为空
            Education education =new Education((String)excel.get(i).get(9),(String)excel.get(i).get(10),(String)excel.get(i).get(11),"", Is211.is211((String)excel.get(i).get(11)),(Date) excel.get(i).get(12));
            //调用插入education表的方法
            userDao.insertEducation(education);
            //3
            UserInfo userInfo =new UserInfo();
            userInfo.setUserName((String)excel.get(i).get(0));
            userInfo.setState((String)excel.get(i).get(1));
            userInfo.setJob((String)excel.get(i).get(2));
            userInfo.setHrName((String)excel.get(i).get(3));
            userInfo.setDept((String)excel.get(i).get(4));
            userInfo.setGender((long)excel.get(i).get(5));
            userInfo.setEmail((String)excel.get(i).get(6));
            userInfo.setBirth((Date) excel.get(i).get(7));
            userInfo.setNativePlace((String)excel.get(i).get(8));
            userInfo.setPhone((String)excel.get(i).get(13));
            userInfo.setEnterTime((Date) excel.get(i).get(14));
            //调用插入userinfo表的方法
            userDao.insertUserInfo(userInfo);
            //判断user中是否有该面试官，如果没有则要添加,

        }
        return "成功";
    }

    private String saveCourses(List<List<Object>> excel){
        //todo
        return null;
    }

    private String saveScore(List<List<Object>> excel){
        //todo

        //插入表头信息
        int a=0,b=0,c=0,d=0;
        String str1="",str2="",str3="",str4="";
        ScoreLabel scoreLabel=new ScoreLabel("",1,0,0,"");
        for(int i=0;i<excel.get(0).size();i++){
            String str=String.valueOf(excel.get(0).get(i));
            if(str.contains("第一阶段\n阶段考试")){
                a=i;
                str1="第一阶段";
            }else if(str.contains("第二阶段")){
                b=i;
                str2="第二阶段";
            }else if(str.contains("第三阶段")) {
                c = i;
                str3="第三阶段";
            }else if(str.contains("4090观察评价")){
                d=i;
                str4="评价阶段";
            }
        }

        //第一阶段信息
        for(int j=a;j<b;j++){
            String labelName=(String)excel.get(1).get(j)+(String)excel.get(2).get(j);
            if(((String)excel.get(1).get(j)).equals("第一阶段成绩")){
                scoreLabel=new ScoreLabel(labelName,j,0,1,"sum");
            }else if(((String)excel.get(1).get(j)).contains("评价")){
                scoreLabel=new ScoreLabel(labelName,j,0,1,"judge");
            }else{
                scoreLabel=new ScoreLabel(labelName,j,0,1,"");
            }
            //调用插入score_label表中
            //insert(scoreLabel);
        }
        //第二阶段信息
        for(int j=b;j<c;j++){
            String labelName=(String)excel.get(1).get(j)+(String)excel.get(2).get(j);
            if(((String)excel.get(1).get(j)).equals("第二阶段成绩")){
                scoreLabel=new ScoreLabel(labelName,j,0,2,"sum");
            }else if(((String)excel.get(1).get(j)).contains("评价")){
                scoreLabel=new ScoreLabel(labelName,j,0,2,"judge");
            }else{
                scoreLabel=new ScoreLabel(labelName,j,0,2,"");
            }
            //调用插入score_label表中
            //insert(scoreLabel);
        }
        //第三阶段信息
        for(int j=c;j<d;j++){
            String labelName=(String)excel.get(1).get(j)+(String)excel.get(2).get(j);

            if(((String)excel.get(1).get(j)).equals("第三阶段成绩")){
                scoreLabel=new ScoreLabel(labelName,j,0,3,"sum");
            }else if(((String)excel.get(1).get(j)).contains("评价")){
                scoreLabel=new ScoreLabel(labelName,j,0,3,"judge");
            }else{
                scoreLabel=new ScoreLabel(labelName,j,0,3,"");
            }
            //调用插入score_label表中
            //insert(scoreLabel);

        }
        //评价阶段
        for(int j=d;j<excel.get(0).size();j++){
            String labelName=(String)excel.get(1).get(j)+(String)excel.get(2).get(j);
            if(((String)excel.get(1).get(j)).equals("严重不符合项")){
                scoreLabel=new ScoreLabel(labelName,j,0,0,"err");
            }else{
                scoreLabel=new ScoreLabel(labelName,j,0,0,"judge");
            }
            //调用插入score_label表中
            //insert(scoreLabel);
        }

        //插入表内容
        for(int i=3;i<excel.size();i++){
            long userId=0;
            String userName=(String)excel.get(i).get(1);
            //调用根据username得出userId方法


            for(int j=2;j<excel.get(i).size();j++){
                long labelIndex=j;
                long valueInt=0;
                String valueString="";
                String value=(String)excel.get(i).get(j);
                boolean isIntOrString=false;
                for (int z=0;z<value.length();z++){
                    if(value.charAt(z)>=48 && value.charAt(z)<=57){
                        isIntOrString=true;
                    }
                }

                if(isIntOrString=true){
                    valueInt=Long.parseLong(value);
                    valueString=null;
                }else{
                    //update label表中的type类型为1，即type为String  根据labelIndex


                    valueInt=0;//接口搜索时应该搜索valueString不是nul
                    valueString=value;
                }
                ScoreValue scoreValue=new ScoreValue(userId,labelIndex,valueInt,valueString,null);
                //调用插入value表信息


            }


        }

        return null;
    }

}
