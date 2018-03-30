package com.sinosoft.stukpisys.servsce;

public class SqlProvider {
    public String getUserParam(String HRName, String job, String school, String education, String major, boolean sex, String state, String belong, boolean is211) {
        String sql="SELECT d.user_name,d.label_name,d.value_string from education,\n" +
                "(SELECT user_info.*,c.* FROM user_info ,\n" +
                "(SELECT user.name,b.* from user, \n" +
                "(select score_value.user_id,score_value.value_string,a.* from score_value,\n" +
                "(select * from score_label where belong='judge') a\n" +
                " where a.label_index=score_value.label_index) b\n" +
                "where user.user_id=b.user_id) c\n" +
                "where user_info.user_name=c.name) d";
        if(HRName!=null||job != null||school != null||education != null||major != null||state != null||belong != null||sex==true||sex==false||is211==true||is211==false){
           sql+= "   where";

        if(HRName!=null){
            sql +=" hr_name"+"="+HRName;
        }
        if (job != null) {
            sql +="  and  job"+"="+job;

        }
        if (school != null) {
            sql+=" and school_name"+"="+school;
          //  WHERE("school_name = #{school}");
        }
        if (education != null) {
            sql+=" and highest_educate"+"="+education;
           // WHERE("highest_educate = #{education}");
        }
        if (major != null) {
            sql+=" and major"+"="+major;
          //  WHERE("major = #{major}");
        }
        if (sex==true) {
            sql+=" and gender"+"="+0;
          //  WHERE("sex = 0");
        }else if (sex==false){
            sql+=" and gender"+"="+1;
           // WHERE("sex = 1");
        }else{
        }
        if (state != null) {
            sql+="  and state"+"="+"'"+state+"'";
          //  WHERE("state = #{state}");
        }
        if (belong != null) {
            sql+="and belong"+"="+belong;
          //  WHERE("belong = #{belong}");
        }
        if (is211 == true) {
            //WHERE("is211 = 0");
            sql+="and is211"+"="+0;
        }else if (is211==false){
            //WHERE("is211 = 1");
            sql+="and is211"+"="+1;
        }else{
        }
        sql+=" and education.edu_id=d.edu_id";
    }
    System.out.print(sql);
    return sql ;
    }
}
