package com.sinosoft.stukpisys.untils;

import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.servsce.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


public class LeadTo {

    //private ScoreDao scoreDao;


/*    private static FileService fileService;
    @Autowired
    public void setscoreDao(FileService fileService){
        this.fileService=fileService;
    }*/

//    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-*.xml");
//    private static FileService fileService = (FileService)applicationContext.getBean(FileService.class);
//    private static AlarmInfoService alarmInfoService = (AlarmInfoService)applicationContext.getBean(AlarmInfoService.class);

   /* public ScoreDao getScoreDao() {
        return scoreDao;
    }

    public void setScoreDao(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public LeadTo(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }
    public LeadTo(){}*/

    public static long is211(String school){
        String school211="北京大学\n" +
                "清华大学\n" +
                "浙江大学\n" +
                "复旦大学\n" +
                "上海交通大学\n" +
                "南京大学\n" +
                "武汉大学\n" +
                "四川大学\n" +
                "中山大学\n" +
                "山东大学\n" +
                "华中科技大学\n" +
                "哈尔滨工业大学\n" +
                "吉林大学\n" +
                "南开大学\n" +
                "中国科学技术大学\n" +
                "西安交通大学\n" +
                "中南大学\n" +
                "东南大学\n" +
                "中国人民大学\n" +
                "大连理工大学\n" +
                "天津大学\n" +
                "厦门大学\n" +
                "北京师范大学\n" +
                "华南理工大学\n" +
                "同济大学\n" +
                "北京航空航天大学\n" +
                "兰州大学\n" +
                "重庆大学\n" +
                "中国农业大学\n" +
                "西北工业大学\n" +
                "北京理工大学\n" +
                "华东师范大学\n" +
                "湖南大学\n" +
                "华东理工大学\n" +
                "苏州大学\n" +
                "南京航空航天大学\n" +
                "郑州大学\n" +
                "华中师范大学\n" +
                "南京农业大学\n" +
                "电子科技大学\n" +
                "东北大学\n" +
                "西南大学\n" +
                "武汉理工大学\n" +
                "上海大学\n" +
                "南京理工大学\n" +
                "东北师范大学\n" +
                "江南大学\n" +
                "西安电子科技大学\n" +
                "华中农业大学\n" +
                "西南交通大学\n" +
                "暨南大学\n" +
                "华北电力大学（北京）\n" +
                "北京科技大学\n" +
                "北京化工大学\n" +
                "东华大学\n" +
                "南京师范大学\n" +
                "北京交通大学\n" +
                "西北农林科技大学\n" +
                "华南师范大学\n" +
                "中国海洋大学\n" +
                "西北大学\n" +
                "陕西师范大学\n" +
                "哈尔滨工程大学\n" +
                "河海大学\n" +
                "南昌大学\n" +
                "北京工业大学\n" +
                "湖南师范大学\n" +
                "福州大学\n" +
                "北京邮电大学\n" +
                "合肥工业大学\n" +
                "云南大学\n" +
                "上海财经大学\n" +
                "中国药科大学\n" +
                "中南财经政法大学\n" +
                "长安大学\n" +
                "广西大学\n" +
                "西南财经大学\n" +
                "安徽大学\n" +
                "太原理工大学\n" +
                "贵州大学\n" +
                "北京林业大学\n" +
                "东北林业大学\n" +
                "中国政法大学\n" +
                "新疆大学\n" +
                "中国传媒大学\n" +
                "四川农业大学\n" +
                "中央财经大学\n" +
                "天津医科大学\n" +
                "辽宁大学\n" +
                "对外经济贸易大学\n" +
                "东北农业大学\n" +
                "河北工业大学\n" +
                "北京中医药大学\n" +
                "上海外国语大学\n" +
                "大连海事大学\n" +
                "中央民族大学\n" +
                "北京外国语大学\n" +
                "内蒙古大学\n" +
                "石河子大学\n" +
                "海南大学\n" +
                "延边大学\n" +
                "宁夏大学\n" +
                "中央音乐学院\n" +
                "北京体育大学\n" +
                "青海大学\n" +
                "北京协和医学院\n" +
                "中国矿业大学（北京）\n" +
                "中国矿业大学（徐州）\n" +
                "西藏大学\n" +
                "解放军国防科学技术大学\n" +
                "解放军第二军医大学\n" +
                "解放军第四军医大学";
        String[] aa=school211.split("\n");
        long bb=0;
        for(int i=0;i<aa.length;i++){
            if(school.equals(aa[i])){
                return 1;
            }else{
                return 0;
            }
        }
        return bb;
    }

/*    public long getLabelIndex(){
        long MaxLabelIndex =0;
        //System.out.print(scoreDao.selectMaxLabelIndex());

        int maxLabelIndex = fileService.selectMaxLabelIndex();
        if (maxLabelIndex!=0){
            MaxLabelIndex=fileService.selectMaxLabelIndex();
        }
        return (MaxLabelIndex+1);
    }*/
}
