package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HODAndCoOrdinatorApprovLeavePojo {
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
