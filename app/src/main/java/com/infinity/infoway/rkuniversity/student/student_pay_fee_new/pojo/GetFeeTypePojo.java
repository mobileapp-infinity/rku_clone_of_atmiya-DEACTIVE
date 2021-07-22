package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFeeTypePojo {

    @SerializedName("FeeHeadId")
    @Expose
    private Integer feeHeadId;
    @SerializedName("FeeHeadName")
    @Expose
    private String feeHeadName;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("StatusMsg")
    @Expose
    private String statusMsg;

    public Integer getFeeHeadId() {
        return feeHeadId;
    }

    public void setFeeHeadId(Integer feeHeadId) {
        this.feeHeadId = feeHeadId;
    }

    public String getFeeHeadName() {
        return feeHeadName;
    }

    public void setFeeHeadName(String feeHeadName) {
        this.feeHeadName = feeHeadName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
