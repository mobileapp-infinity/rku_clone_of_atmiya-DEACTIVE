package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeeUrlPojo {

    @SerializedName("res_status")
    @Expose
    private Integer resStatus;
    @SerializedName("res_message")
    @Expose
    private String resMessage;

    public Integer getResStatus() {
        return resStatus;
    }

    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

}
