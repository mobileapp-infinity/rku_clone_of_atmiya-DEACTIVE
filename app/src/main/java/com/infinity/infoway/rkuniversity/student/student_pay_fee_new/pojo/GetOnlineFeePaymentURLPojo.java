package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOnlineFeePaymentURLPojo {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("StatusMsg")
    @Expose
    private String statusMsg;

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
