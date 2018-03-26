package com.sinosoft.stukpisys.entity;

import java.util.Date;

public class DetailValue {
    private  Integer valueId;
    private Integer userId;
    private  Integer labelIndex;
    private Integer valueInt;
    private String valueString;
    private Date valueDate;

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLabelIndex() {
        return labelIndex;
    }

    public void setLabelIndex(Integer labelIndex) {
        this.labelIndex = labelIndex;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    @Override
    public String toString() {
        return "DetailValue{" +
                "valueId=" + valueId +
                ", userId=" + userId +
                ", labelIndex=" + labelIndex +
                ", valueInt=" + valueInt +
                ", valueString='" + valueString + '\'' +
                ", valueDate=" + valueDate +
                '}';
    }
}
