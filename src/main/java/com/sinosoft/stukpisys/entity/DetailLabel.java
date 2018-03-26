package com.sinosoft.stukpisys.entity;

public class DetailLabel {
    private Integer labelId;
    private String labelName;
    private Integer labelIndex;
    private Integer type;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getLabelIndex() {
        return labelIndex;
    }

    public void setLabelIndex(Integer labelIndex) {
        this.labelIndex = labelIndex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DetailLabel{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", labelIndex=" + labelIndex +
                ", type=" + type +
                '}';
    }
}
