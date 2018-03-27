package com.sinosoft.stukpisys.entity;

public class Education {
    private Integer eduId;
    private String highestEdu;
    private String major;
    private String school;
    private String type;
    private String graduationTime;

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    public String getHighestEdu() {
        return highestEdu;
    }

    public void setHighestEdu(String highestEdu) {
        this.highestEdu = highestEdu;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    @Override
    public String toString() {
        return "Education{" +
                "eduId=" + eduId +
                ", highestEdu='" + highestEdu + '\'' +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                ", type='" + type + '\'' +
                ", graduationTime='" + graduationTime + '\'' +
                '}';
    }
}
