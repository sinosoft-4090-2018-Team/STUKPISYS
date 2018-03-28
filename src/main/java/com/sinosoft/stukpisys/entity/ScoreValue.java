package com.sinosoft.stukpisys.entity;

public class ScoreValue {

  private long valueId;
  private long userId;
  private long labelIndex;
  private long valueInt;
  private String valueString;
  private java.sql.Date valueDate;


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

}
