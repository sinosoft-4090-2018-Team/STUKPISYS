package com.sinosoft.stukpisys.servsce;

public class SqlProvider {

    public String getUserParam(String HRName, String job, String school, String education, String major, String sex, String state, String belong, String is211,String enterTime) {
        String sql="SELECT d.user_name,d.label_name,d.value_string from education,\n" +
                "(SELECT user_info.*,c.* FROM user_info ,\n" +
                "(SELECT user.name,b.* from user, \n" +
                "(select score_value.user_id,score_value.value_string,a.* from score_value,\n" +
                "(select * from score_label where belong='judge') a\n" +
                " where a.label_index=score_value.label_index) b\n" +
                "where user.user_id=b.user_id) c\n" +
                "where user_info.user_name=c.name) d";
        if(HRName!="全部"||job != "全部"||school != "全部"||education != "全部"||major != "全部"||state != null||belong != null||sex!="全部"||is211!=null||enterTime!=null)
        {
        if(true){
            sql+= "   where 1=1";
        }
        if(!"全部".equals(HRName)&&HRName!=null){
            sql +=" and hr_name"+"="+"'"+HRName+"'";
        }
        if (!"全部".equals(job)&&job!=null) {
            sql +=" and  job"+"="+"'"+job+"'";

        }
        if (!"全部".equals(school)  &&school!=null) {
            sql+=" and school_name"+"="+"'"+school+"'";
            //  WHERE("school_name = #{school}");
        }
        if (!"全部".equals(education)&&education!=null) {
            sql+=" and highest_educate"+"="+"'"+education+"'";
            // WHERE("highest_educate = #{education}");
        }
        if (!"全部".equals(major) &&major!=null) {
            sql+=" and major"+"="+"'"+major+"'";
            //  WHERE("major = #{major}");
        }
        if (!"全部".equals(sex)&&sex!=null) {
            sql+=" and gender"+"="+"'"+sex+"'";
            //  WHERE("sex = 0");
        }
        if (state != null) {
            sql+="  and state"+"="+"'"+state+"'";
            //sql+="  and state"+"="+state;
            //  WHERE("state = #{state}");
        }
        if (belong != null) {
            sql+="and belong"+"="+"'"+belong+"'";
            //  WHERE("belong = #{belong}");
        }
        if (is211 != null) {
            //WHERE("is211 = 0");
            sql+="and is211"+"="+"'"+is211+"'";
        }
        if (enterTime != null) {
                //WHERE("is211 = 0");
            sql+="and enterTime"+"="+"'"+enterTime+"'";
        }

        sql += " and education.edu_id=d.edu_id";


    }else {
        sql+="  where education.edu_id=d.edu_id";
    }
        System.out.print(sql);
        return sql ;
    }

    public String   getUserScoreParam(String HRName, String job, String school, String education, String major, String sex, String state, String belong, String is211,String enterTime){
        String sql="SELECT d.user_name,d.label_name,d.value_int from education,\n" +
                "(SELECT user_info.*,c.* FROM user_info ,\n" +
                "(SELECT user.name,b.* from user, \n" +
                "(select score_value.user_id,score_value.value_int,a.* from score_value,\n" +
                "(select * from score_label where belong='sum') a\n" +
                " where a.label_index=score_value.label_index) b\n" +
                "where user.user_id=b.user_id) c\n" +
                "where user_info.user_name=c.name) d  ";

        if(HRName!="全部"||job != "全部"||school != "全部"||education != "全部"||major != "全部"||state != null||belong != null||sex!="全部"||is211!=null||enterTime!=null)
        {
            if(true){
                sql+= "   where 1=1";
            }
            if(!"全部".equals(HRName)&&HRName!=null){
                sql +=" and hr_name"+"="+"'"+HRName+"'";
            }
            if (!"全部".equals(job)&&job!=null) {
                sql +=" and  job"+"="+"'"+job+"'";

            }
            if (!"全部".equals(school)  &&school!=null) {
                sql+=" and school_name"+"="+"'"+school+"'";
                //  WHERE("school_name = #{school}");
            }
            if (!"全部".equals(education)&&education!=null) {
                sql+=" and highest_educate"+"="+"'"+education+"'";
                // WHERE("highest_educate = #{education}");
            }
            if (!"全部".equals(major) &&major!=null) {
                sql+=" and major"+"="+"'"+major+"'";
                //  WHERE("major = #{major}");
            }
            if (!"全部".equals(sex)&&sex!=null) {
                sql+=" and gender"+"="+"'"+sex+"'";
                //  WHERE("sex = 0");
            }
            if (state != null) {
                sql+="  and state"+"="+"'"+state+"'";
                //sql+="  and state"+"="+state;
                //  WHERE("state = #{state}");
            }
            if (belong != null) {
                sql+="and belong"+"="+"'"+belong+"'";
                //  WHERE("belong = #{belong}");
            }
            if (is211 != null) {
                //WHERE("is211 = 0");
                sql+="and is211"+"="+"'"+is211+"'";
            }
            if (enterTime != null) {
                //WHERE("is211 = 0");
                sql+="and enterTime"+"="+"'"+enterTime+"'";
            }

            sql += " and education.edu_id=d.edu_id";
        }
        System.out.print(sql);
        return sql ;
    }
}

