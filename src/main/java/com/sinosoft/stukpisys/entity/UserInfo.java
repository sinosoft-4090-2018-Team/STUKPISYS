package com.sinosoft.stukpisys.entity;

public class UserInfo {
    private Integer infoId;
    private String userName;
    private String state;
    private String hrName;
    private String job;
    private  String gender;
    private String email;
    private String birth;
    private String nativePlace;
    private  String call;
    private String enterTime;
    private  Integer eduId;
    private String dept;

    public UserInfo() {
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "infoId=" + infoId +
                ", userName='" + userName + '\'' +
                ", state='" + state + '\'' +
                ", hrName='" + hrName + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", call='" + call + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", eduId=" + eduId +
                ", dept='" + dept + '\'' +
                '}';
    }
}
