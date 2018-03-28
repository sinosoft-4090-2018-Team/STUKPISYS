package com.sinosoft.stukpisys.entity;

public class ScoreLabel {
    private Integer labelId;
    private String labelName;
    private Integer labelIndex;
    private Integer type;
    private String stage;

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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "ScoreLabel{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", labelIndex=" + labelIndex +
                ", type=" + type +
                ", stage='" + stage + '\'' +
                '}';
    }
}
