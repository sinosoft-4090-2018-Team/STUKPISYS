package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.dao.InfoDao;
import com.sinosoft.stukpisys.dao.ScoreDao;
import com.sinosoft.stukpisys.dao.UserDao;
import com.sinosoft.stukpisys.entity.*;
import com.sinosoft.stukpisys.servsce.HRService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class HRServiceImpl implements HRService{

    @Autowired
    private InfoDao infoDao;
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getPersonInfoByName(String name) {
        return infoDao.getPersonInfoByName(name);
    }

    @Override
    public List<UserInfo> getUserInfoByState(String state) {
        return infoDao.getInfoByState(state);
    }

    @Override
    public List<UserInfo> getUserIdByhrName(String hrName) {
        return infoDao.getUserIdByhrName(hrName);
    }


    @Override
    public List<UserInfo> getUserByJob(String job) {
        return infoDao.getUserByJob(job);
    }

    @Override
    public List<UserInfo> getUserBySex(String sex) {

        return infoDao.getUserBySex(sex);
    }

    @Override
    public List<UserInfo> getUserByEnterTime(String enterTime) {
        return infoDao.getUserByEnterTime(enterTime);
    }

    @Override
    public List<UserInfo> getUserBySchool(String name) {
        return infoDao.getUserBySchoolName(name);
    }

    @Override
    public List<List<Object>> getUserScoreByStageAndTime(int stage, Date enter_time) {
//        return scoreDao.getUserScoreByStageAndTime(stage,enter_time);
        return null;
    }

    @Override
    public List<Long> getScoreInbackByStage2(int stage) {
        List<Long> l1=scoreDao.getScoreInbackByStage(1);
        List<Long> l2=scoreDao.getScoreInbackByStage(2);
        List<Long> l3=scoreDao.getScoreInbackByStage(3);

        List<Long> l4=new ArrayList();
        l4.addAll(l1);
        l4.addAll(l2);
        l4.addAll(l3);
        Set<Long> set = new HashSet<>(l4);
        Collection rs = CollectionUtils.disjunction(l4,set);
        List<Long> list1 = new ArrayList<>(rs);
        Set<Long> set2 = new HashSet<>(list1);
        List<Long> list2 = new ArrayList<>(set2);
        return list2;
    }

    @Override
    public List<Long> getScoreInbackByStage(int stage) {
        List<Long> l1=scoreDao.getScoreInbackByStage(1);
        List<Long> l2=scoreDao.getScoreInbackByStage(2);
        List<Long> l3=scoreDao.getScoreInbackByStage(3);
        l1.retainAll(l2);
        l1.retainAll(l3);


        return l1;
    }

    @Override
    public List<Object> getScoreById(long id) {
        return scoreDao.getScoreById(id);
    }

    @Override
    public List<ScoreLabel> getScoreInback(int stages) {
        return null;
    }

    @Override
    public List<ScoreLabel> getJudgeInback(long id) {
        return scoreDao.getJudgeById(id);
    }

    @Override
    public List<ScoreLabel> getTraineePass(Long id) {
        return scoreDao.getTraineePass(id);
    }

    @Override
    public List<ScoreLabel> getTraineeScore(String name) {
        return infoDao.getTraineeScore(name);
    }

    @Override
    public List<ScoreLabel> getTraineeJudge(String name) {
        return infoDao.getTraineeJudge(name);
    }


    @Override
    public List<ScoreValue> getFirstSealScore() {
        return scoreDao.getFirstSealScore();
    }

    @Override
    public List<ScoreValue> getGoodSealScore() {
        return scoreDao.getGoodSealScore();
    }

    @Override
    public List<ScoreValue> getUsualPerformance() {
        return scoreDao.getUsualPerformance();
    }

    @Override
    public int insertScoreLabel(ScoreLabel scoreLabel) {
        long labelIndex = scoreDao.selectMaxLabelIndex();
        if (labelIndex!=0){
            labelIndex=scoreDao.selectMaxLabelIndex();
        }
        scoreLabel.setLabelIndex(labelIndex+1);
        System.out.print(labelIndex);
        return scoreDao.insertScoreLabel(scoreLabel);
    }

    @Override
    public List<ScoreLabel> selectScoreLabel() {
        return scoreDao.selectScoreLabel();
    }

    @Override
    public int updateLabelName(String newLabelName, String labelName) {
        return scoreDao.updateLabelName(newLabelName,labelName);
    }

    @Override
    public List<UserInfo> getUserByHighestEducate(String educate) {
        return infoDao.getUserByHighestEducate(educate);
    }

    @Override
    public List<Map<String,Integer>> getPopulationBySexDiffer() {
        return  infoDao.getPopulationBySexDiffer();
    }

    @Override
    public List<Map<String,Integer>> getPopulationByEducationDiffer() {
        return infoDao.getPopulationByEducationDiffer();
    }

    @Override
    public List<String> getDifferEducateName() {
        return infoDao.getDifferEducateName();
    }

    @Override
    public List<Map<String,Integer>> getPopulationByMajorDiffer() {
        return infoDao.getPopulationByMajorDiffer();
    }

    @Override
    public List<String> getDifferMajorName() {
        return infoDao.getDifferMajorName();
    }

    @Override
    public List<Map<String,Integer>>  getPopulationByIs211() {
        return infoDao.getPopulationByIs211( );
    }

    @Override
    public List<Map<String,Integer>> getPopulationByLocationDiffer() {
        return infoDao.getPopulationByLocationDiffer();
    }

    @Override
    public List<String> getDifferLocationName() {
        return infoDao.getDifferLocationName();
    }


    @Override
    public List<ScoreValue> getJudgeByParam(String HRName,String job,String school,String Education,String major,String sex,boolean isFired,boolean isSimple,boolean isNew,boolean hasErr,String is211)
    {
        String state=null;
        if (isFired==true){
            state="淘汰";
        }else if(isNew==true){
            state="特殊";
        }else if(isSimple==true) {
            state="正常";
        }else{
            state=null;
        }
        String belong=null;
        if(hasErr==true){
            belong="err";
        }
        return infoDao.getJudgeByParam( HRName,job,school,Education,major, sex,state,belong,is211);
    }

    @Override
    public List<ScoreValue> getUserScoreParam(String HRName, String job, String school, String Education, String major, String sex,boolean isSimple, boolean isFired, boolean isNew, boolean hasErr, String is211) {
        String state=null;
        if (isFired==true){
            state="淘汰";
        }else if(isNew==true){
            state="特殊";
        }else if(isSimple==true) {
            state="正常";
        }else{
            state=null;
        }
        String belong=null;
        if(hasErr==true){
            belong="err";
        }
         return infoDao.getUserScoreParam( HRName,job,school,Education,major, sex,state,belong,is211);
    }
    @Override
    public Education getEduInfoByUserName(String userName) {
        return  infoDao.getEduInfoByUserName(userName);
    }

    @Override
    public List<List<Object>> getScoreFromStageByUser_id(int userId, int stage) {
        return null;
    }

   /* @Override
    public List<List<Object>> getScoreFromStageByUser_id(long userId, int stage) {
        return scoreDao.getScoreFromStageByUser_id(userId,stage);
    }*/


    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public List<String> gethrName() {
        return infoDao.gethrName();
    }

    @Override
    public List<String> getJob() {
        return infoDao.getJob();
    }

    @Override
    public List<String> getschoolName() {
        return infoDao.getschoolName();
    }

    @Override
    public List<String> getHighestEducate() {
        return infoDao.getHighestEducate();
    }

    @Override
    public List<String> getMajor() {
        return infoDao.getMajor();
    }

    @Override
    public List<Integer> getScoreList(Integer id) {
        return null;
    }


}
