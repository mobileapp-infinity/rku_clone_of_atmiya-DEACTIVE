package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayFeeTypePojoList {
    @SerializedName("Fee_Type_Id")
    @Expose
    private Integer feeTypeId;
    @SerializedName("Fee_Type")
    @Expose
    private String feeType;
    @SerializedName("Fee_Type_Status")
    @Expose
    private Integer feeTypeStatus;

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getFeeTypeStatus() {
        return feeTypeStatus;
    }

    public void setFeeTypeStatus(Integer feeTypeStatus) {
        this.feeTypeStatus = feeTypeStatus;
    }

}
