package com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertMsgPojo {

    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
