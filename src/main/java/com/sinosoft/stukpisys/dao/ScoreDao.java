package com.sinosoft.stukpisys.dao;

import com.sinosoft.stukpisys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import sun.reflect.generics.tree.VoidDescriptor;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Insert("INSERT INTO score_value(user_id,label_index,value_int,value_string,value_date) VALUES(#{userId},#{labelIndex},#{valueInt},#{valueString},#{valueDate})")
    void insertScoreValue(ScoreValue scoreValue);
    @Select("SELECT * FROM score_label WHERE label_name=#{labelName},type=#{type}")
    ScoreLabel selectLabelByLabelNameAndType(String labelName,long type);




    // List<List<Object>> getScoreFromStageByUser_id(long userId,int stage);

    //根据名字查id
    @Select("select user_id from user where name=#{name}")
    User getUserIdByUserName(String name);
    //修改评价
    @Update("update score_value set value_string=#{judge} where user_id=#{userId} and label_name=#{labelName}")
    int changeJudgeByUserNameAndLabelName(@RequestParam String judge,@RequestParam int userId,@RequestParam String labelName);

}
