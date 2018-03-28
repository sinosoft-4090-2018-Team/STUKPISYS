package com.sinosoft.stukpisys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ScoreDao {
    //按阶段和时间查看总分数（排序）
    @Select("SELECT\n" +
            "\tenter_time,\n" +
            "\tuser_info.user_name,\n" +
            "\tt.value_int\n" +
            "FROM\n" +
            "\tuser_info,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tNAME,\n" +
            "\t\t\tscore_value.user_id,\n" +
            "\t\t\tvalue_int\n" +
            "\t\tFROM\n" +
            "\t\t\tuser,\n" +
            "\t\t\tscore_value\n" +
            "\t\tWHERE\n" +
            "\t\t\tscore_value.label_index = (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tlabel_index\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tscore_label\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tstage = #{stage}\n" +
            "\t\t\t\tAND isSum = 1\n" +
            "\t\t\t)\n" +
            "\t\tAND user .user_id = score_value.user_id\n" +
            "\t\tORDER BY\n" +
            "\t\t\tscore_value.value_int\n" +
            "\t) t\n" +
            "WHERE\n" +
            "\tuser_info.user_name = t. NAME\n" +
            "AND enter_time = \"#{enter_time}\"")
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


}
