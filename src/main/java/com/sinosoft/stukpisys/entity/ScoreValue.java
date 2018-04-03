package com.sinosoft.stukpisys.entity;

import java.sql.Date;

public class ScoreValue {

  private long valueId;
  private long userId;
  private long labelIndex;
  private long valueInt;
  private String valueString;
  private java.sql.Date valueDate;
  private String userName;
  private String labelName;
  private long score;

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }

  public long getValueId() {
    return valueId;
  }

  public void setValueId(long valueId) {
    this.valueId = valueId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getLabelIndex() {
    return labelIndex;
  }

  public void setLabelIndex(long labelIndex) {
    this.labelIndex = labelIndex;
  }


  public long getValueInt() {
    return valueInt;
  }

  public void setValueInt(long valueInt) {
    this.valueInt = valueInt;
  }


  public String getValueString() {
    return valueString;
  }

  public void setValueString(String valueString) {
    this.valueString = valueString;
  }


  public java.sql.Date getValueDate() {
    return valueDate;
  }

  public void setValueDate(java.sql.Date valueDate) {
    this.valueDate = valueDate;
  }

  public ScoreValue(){}
  public ScoreValue(long userId, long labelIndex, long valueInt, String valueString, Date valueDate) {
    this.userId = userId;
    this.labelIndex = labelIndex;
    this.valueInt = valueInt;
    this.valueString = valueString;
    this.valueDate = valueDate;
  }
  public ScoreValue(long userId, long labelIndex, long valueInt, String valueString, Date valueDate,String userName,String labelName) {
    this.userId = userId;
    this.labelIndex = labelIndex;
    this.valueInt = valueInt;
    this.valueString = valueString;
    this.valueDate = valueDate;
    this.userName=userName;
    this.labelName=labelName;
  }


  @Override
  public String toString() {
    return "ScoreValue{" +
            "valueId=" + valueId +
            ", userId=" + userId +
            ", labelIndex=" + labelIndex +
            ", valueInt=" + valueInt +
            ", valueString='" + valueString + '\'' +
            ", valueDate=" + valueDate +
            ", userName='" + userName + '\'' +
            ", labelName='" + labelName + '\'' +
            '}';
  }
}
