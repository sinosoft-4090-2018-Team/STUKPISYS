package com.sinosoft.stukpisys.entity;


public class ScoreLabel {

  private long labelId;
  private String labelName;
  private long labelIndex;
  private long type;
  private long stage;
  private long isSum;


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


  public long getIsSum() {
    return isSum;
  }

  public void setIsSum(long isSum) {
    this.isSum = isSum;
  }

}
