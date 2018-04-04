package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.ScoreLabel;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.tree.VoidDescriptor;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;
@Mapper
public interface ScoreDao {

    //按阶段和时间查看总分数（排序）
    @Select("SELECT\n" +
            "\tm. NAME,\n" +
            "\tsum(m.value_int),\n" +
            "  m.enter_time\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tc.*, user_info.enter_time\n" +
            "\t\tFROM\n" +
            "\t\t\tuser_info,\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tuser . NAME,\n" +
            "\t\t\t\t\tn.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tuser,\n" +
            "\t\t\t\t\t(\n" +
            "\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\tscore_value.*\n" +
            "\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\tscore_value,\n" +
            "\t\t\t\t\t\t\t(\n" +
            "\t\t\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\t\t\t*\n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tscore_label\n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tbelong='sum'\n" +
            "\t\t\t\t\t\t\t) a\n" +
            "\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\tscore_value.label_index = a.label_index\n" +
            "\t\t\t\t\t) n\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tuser .user_id = n.user_id\n" +
            "\t\t\t) c\n" +
            "\t\tWHERE\n" +
            "\t\t\tuser_info.enter_time = #{enter_time}\n" +
            ")m\n" +
            "\t\tGROUP BY\n" +
            "\t\t\tm.user_id\n")
    List<List<Object>> getUserScoreByStageAndTime(int stage, java.sql.Date enter_time);

    //实习生三个阶段的总成绩
    @Select("SELECT\n" +
            "\tm. NAME,\n" +
            "\tsum(m.value_int),\n" +
            "  m.enter_time\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tc.*, user_info.enter_time\n" +
            "\t\tFROM\n" +
            "\t\t\tuser_info,\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tuser . NAME,\n" +
            "\t\t\t\t\tn.*\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tuser,\n" +
            "\t\t\t\t\t(\n" +
            "\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\tscore_value.*\n" +
            "\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\tscore_value,\n" +
            "\t\t\t\t\t\t\t(\n" +
            "\t\t\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\t\t\t*\n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tscore_label\n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tisSum = 1\n" +
            "\t\t\t\t\t\t\t) a\n" +
            "\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\tscore_value.label_index = a.label_index\n" +
            "\t\t\t\t\t) n\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tuser .user_id = n.user_id\n" +
            "\t\t\t) c\n" +
            "\t\tWHERE\n" +
            "\t\t\tuser_info.enter_time = #{enter_time}\n" +
            ")m\n" +
            "\t\tGROUP BY\n" +
            "\t\t\tm.user_id")
    List<List<Object>>  getUserAllScoreByTime(java.sql.Date enter_time);

    /*
    查看某同学某阶段各成绩的学分情况，通过员工id和阶段下标
    默认Stage 情况：第一阶段各小考核的stage值为0；
    第一阶段总考核成绩stage值为1；第二阶段总考核成绩stage值为2；第三阶段总考核成绩stage值为3
     */
    @Select("SELECT  l.label_name, \n" +
            "IF(l.stage=0 ,IF(v.value_int>=90,5,\n" +
            "IF(v.value_string='合格',3,0)),v.value_int)\n" +
            "detailScore  \n" +
            "FROM score_label l JOIN score_value v\n" +
            "ON l.label_index=v.label_index\n" +
            "where user_id=#{0} AND l.stage=#{1}")
     List<List<Object>> getScoreFromStageByUser_id(int userId,int stage);
    //插入Score_label表--米晓锐
    @Insert("INSERT INTO score_label(label_name,label_index,type,stage,belong) VALUES(#{labelName},#{labelIndex},#{type},#{stage},#{belong})")
    void insertScore_label(ScoreLabel scoreLabel);
    //--米晓锐
    @Update("UPDATE score_label SET type=1 WHERE label_index=#{labelIndex}")
    void updateScoreLabel(long labelIndex);
    //--米晓锐
    @Select("SELECT type FROM score_label WHERE label_index=#{labelIndex}")
    long selectTypeis1(long labelIndex);
    //--米晓锐
    @Insert("INSERT INTO score_value(user_id,label_index,value_int,value_string,value_date) VALUES(#{userId},#{labelIndex},#{valueInt},#{valueString},#{valueDate})")
    void insertScoreValue(ScoreValue scoreValue);
    @Select("SELECT * FROM score_label WHERE label_name=#{labelName} and stage=#{stage}")
    ScoreLabel selectLabelByLabelNameAndType(@Param("labelName") String labelName,@Param("stage")long stage);
    //--mxr
    @Select("SELECT IFNULL(MAX(label_index),1) FROM score_label")
    long selectMaxLabelIndex();
    //--mxr
    @Select("SELECT label_index FROM score_label WHERE label_name=#{labelName}")
    long selectLabelIndexByLabelName(String labelName);
//    @Select("SELECT * FROM score_label WHERE label_name=#{labelName}")
//    ScoreLabel selectByLabelName(String labelName);



    // List<List<Object>> getScoreFromStageByUser_id(long userId,int stage);

    //根据名字查id
    @Select("select user_id from user where name=#{name}")
    User getUserIdByUserName(String name);
    //根据labelName找label_index
    @Select("select label_index from score_label where label_name=#{labelName}")
    ScoreLabel getLabelIndexByLabelName(String labelName);
    //修改评价
    @Update("update score_value set value_string=#{judge} where user_id=#{userId} and label_index=#{labelIndex}")
    int changeJudgeByUserNameAndLabelName(@Param("userId")long  userId, @Param("labelIndex") long labelIndex, @Param("judge") String judge);

    //修改实习生信息
    @Update("UPDATE user_info set state=#{state},hr_name=#{hrName},job=#{job},gender=#{gender},email=#{email},birth=#{birth},native_place=#{nativePlace},phone=#{phone},enter_time=#{enterTime},edu_id=#{eduId},dept=#{dept}\n" +
            "\n" +
            "where user_name=#{userName} ")
    int changeInfoByUsernameAndKeyValue(@Param("state")String  state, @Param("hrName")String  hrName, @Param("job")String  job, @Param("gender")long  gender, @Param("email")String  email, @Param("birth")Date birth, @Param("nativePlace")String  nativePlace, @Param("phone")String  phone, @Param("enterTime")Date  enterTime, @Param("eduId")long  eduId, @Param("dept")String  dept, @Param("userName")String  userName);

    //根据阶段查成绩倒5的人的id
    @Select("select user_id from score_value where label_index=(select label_index from score_label where belong='sum' and stage=#{stage}) order by value_int limit 5")
    List<Long> getScoreInbackByStage(int stage);
    //根据id查三阶段的成绩
    @Select("(select name from user where user_id=#{id})\n" +
            "union all\n" +
            "(select value_int from score_value where user_id=#{id} and (label_index=(select label_index from score_label where belong='sum' and stage=1) \n" +
            "or label_index=(select label_index from score_label where belong='sum' and stage=2) or label_index=(select label_index from score_label where belong='sum' and stage=3)))")
    List<Object> getScoreById(long id);




    //根据id的返回
    @Select("select v.name,d.label_name,d.value_string from (select name,user_id from user where user_id=#{id}) v left join (select b.user_id,a.label_name,b.value_string from(select label_name,label_index from score_label where belong='judge' and type=1) a left join \n" +
            "(SELECT user_id,value_string,label_index from score_value where user_id=#{id}) b on a.label_index=b.label_index) d on v.user_id=d.user_id")
    List<ScoreLabel> getJudgeById(long id);

   //获取第一名章的得分（个数*2）
    @Select("select score_value.*,value_int*2 as score from score_value,\n" +
            "(SELECT * from  score_label  where label_name like '%第一名章%')a\n" +
            "where score_value.label_index=a.label_index")
    List<ScoreValue> getFirstSealScore();
    //获取good章的得分（个数*1）
    @Select("select score_value.*,value_int as score from score_value,\n" +
            "(SELECT * from  score_label  where label_name like '%good章%')a\n" +
            "where score_value.label_index=a.label_index")
    List<ScoreValue> getGoodSealScore();
    //获取第一阶段出勤和平时成绩
    @Select("select score_value.*,sum(value_int) as score from score_value,\n" +
            "(select * from score_label where stage=1 and belong ='score' ) a \n" +
            "where score_value.label_index=a.label_index  \n" +
            " GROUP BY score_value.user_id")
    List<ScoreValue> getUsualPerformance();

    //通过id获得合不合格和请假天数
    @Select("(select s.label_name,a.value_string as value from score_label s join (select user_id,label_index,value_string from score_value where user_id=#{id} and (value_string='合格' or '不合格')) a on s.label_index=a.label_index)\n" +
            "union all\n" +
            "(select s.label_name,a.value_int as value from score_label s join (SELECT value_int,label_index from score_value where label_index=(SELECT label_index from score_label where belong='absence') and user_id=37)a on a.label_index=s.label_index)")
    List<ScoreLabel> getTraineePass(Long id);

}
