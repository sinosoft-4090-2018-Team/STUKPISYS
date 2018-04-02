package com.sinosoft.stukpisys.entity;


public class UserInfo {

  private long infoId;
  private String userName;
  private String state;
  private String hrName;

  private String job;
  private long gender;//0为男，1为女
  private String email;
  private java.sql.Date birth;
  private String nativePlace;
  private String phone;
  private java.sql.Date enterTime;
  private long eduId;
  private String dept;


  public long getInfoId() {
    return infoId;
  }

  public void setInfoId(long infoId) {
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


  public long getGender() {
    return gender;
  }

  public void setGender(long gender) {
    this.gender = gender;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public java.sql.Date getBirth() {
    return birth;
  }

  public void setBirth(java.sql.Date birth) {
    this.birth = birth;
  }


  public String getNativePlace() {
    return nativePlace;
  }

  public void setNativePlace(String nativePlace) {
    this.nativePlace = nativePlace;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public java.sql.Date getEnterTime() {
    return enterTime;
  }

  public void setEnterTime(java.sql.Date enterTime) {
    this.enterTime = enterTime;
  }


  public long getEduId() {
    return eduId;
  }

  public void setEduId(long eduId) {
    this.eduId = eduId;
  }


  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

}
