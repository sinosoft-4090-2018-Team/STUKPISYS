package com.sinosoft.stukpisys.entity;


public class ScoreLabel {

  private long labelId;
  private String labelName;
  private long labelIndex;
  private String name;
  private long type;
  private long stage;
  private String belong;
  private Object value;
   private long valueInt;
   private String valueString;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getValueString() {
    return valueString;
  }

  public void setValueString(String valueString) {
    this.valueString = valueString;
  }

  public long getValueInt() {
    return valueInt;
  }

  public void setValueInt(long valueInt) {
    this.valueInt = valueInt;
  }

  public long getLabelId() {
    return labelId;
  }

  public void setLabelId(long labelId) {
    this.labelId = labelId;
  }


  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }


  public long getLabelIndex() {
    return labelIndex;
  }

  public void setLabelIndex(long labelIndex) {
    this.labelIndex = labelIndex;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getStage() {
    return stage;
  }

  public void setStage(long stage) {
    this.stage = stage;
  }


  public String getBelong() {
    return belong;
  }

  public void setBelong(String belong) {
    this.belong = belong;
  }

  public  ScoreLabel(){}

  public ScoreLabel(String labelName, long labelIndex, long type, long stage, String belong) {
    this.labelName = labelName;
    this.labelIndex = labelIndex;
    this.type = type;
    this.stage = stage;
    this.belong = belong;
  }
}
